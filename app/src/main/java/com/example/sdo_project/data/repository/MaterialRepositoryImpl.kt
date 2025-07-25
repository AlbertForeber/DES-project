package com.example.sdo_project.data.repository

import com.example.sdo_project.data.remote.dto.MaterialDto
import com.example.sdo_project.data.remote.dto.MaterialSectionDto
import com.example.sdo_project.data.remote.dto.NewMaterialDto
import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.time.ExperimentalTime

class MaterialRepositoryImpl(
    private val client: SupabaseClient
): MaterialRepository {

    override suspend fun getMaterials(parent: MaterialSection): Result<List<Material>> {
        try {
            val result = client.from("material")
                .select{
                    filter {
                        eq("section_id", parent.id)
                    }
                }.decodeList<MaterialDto>().map { it.toDomain() }

            return Result.success(result)

        }catch (e:Exception){
            return Result.failure(e)
        }
    }

    override suspend fun addMaterial(material: Material): Result<Unit> {
        try {
            client.from("material").insert(material.toData())
            return Result.success(Unit)
        }catch (e: Exception){
           return  Result.failure(e)
        }
    }

    override suspend fun getMaterialSections(disciplineId: Int, parentId: Int?): Result<List<MaterialSection>> {
        try {
            if (parentId != null) {
                val result = client.from("material_section")
                    .select {
                        filter {
                            eq ("discipline_id", disciplineId)
                            eq("parent_id", parentId)


                        }
                    }.decodeList<MaterialSectionDto>().map { it.toDomain() }
                return Result.success(result)

            } else {
                val result = client.postgrest.rpc("get_main_material_sections",
                    buildJsonObject {
                        put("discipline_id_param", disciplineId)

                    }
                ).decodeList<MaterialSectionDto>().map { it.toDomain() }

                return Result.success(result)
            }

        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun MaterialDto.toDomain(): Material{
        return Material(
            id  = id,
            sectionId = sectionId,
            accessTime = accessTime.toString(),
            content = content,
            name = name
        )
    }

    private fun Material.toData():NewMaterialDto{
        return NewMaterialDto(
            sectionId = sectionId,
            accessTime = accessTime,
            content = content,
            name=name
        )
    }

    private fun MaterialSectionDto.toDomain(): MaterialSection{
        return MaterialSection(
            id  = id,
            name = name,
            parentId = parentId,
            disciplineId = disciplineId
        )
    }
}