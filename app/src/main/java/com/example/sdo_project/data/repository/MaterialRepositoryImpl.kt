package com.example.sdo_project.data.repository

import android.util.Log
import com.example.sdo_project.data.remote.dto.MaterialDto
import com.example.sdo_project.data.remote.dto.MaterialSectionDto
import com.example.sdo_project.data.remote.dto.NewMaterialDto
import com.example.sdo_project.data.remote.dto.NewMaterialSectionDto
import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.exception.PostgrestRestException
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
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
        }
        catch (e: PostgrestRestException){
            return  Result.failure(e)
        }
        catch (e: Exception){
           return  Result.failure(e)
        }
    }

    override suspend fun getMaterialSections(disciplineId: Int): Result<List<MaterialSection>> {
        return try {
            val result = client.postgrest.rpc(
                "get_main_material_sections",
                buildJsonObject {
                    put("discipline_id_param", disciplineId)

                }
            ).decodeList<MaterialSectionDto>().map { it.toDomain() }

            Result.success(result)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMaterialSections(parent: MaterialSection): Result<List<MaterialSection>> {
        return try {
            val result = client.from("material_section")
                .select {
                    filter {
                        eq("parent_id", parent.id)


                    }
                }.decodeList<MaterialSectionDto>().map { it.toDomain() }
            Result.success(result)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addMaterialSection(section: MaterialSection): Result<Unit> {
        try {
            client.from("material_section").insert(section.toData())
            return Result.success(Unit)
        }
        catch (e: PostgrestRestException){
            return  Result.failure(e)
        }
        catch (e: Exception){
            return  Result.failure(e)
        }
    }

    override suspend fun addMaterialSections(sections: List<MaterialSection>, initialParent: Int?): Result<Unit> {
        val newSections = sections.map { it.toDataNull() }


        try {
            client.postgrest.rpc("add_many_sections",
                buildJsonObject {
                    put ("initial_parent_id", initialParent)
                    put("sections", Json.encodeToJsonElement(newSections))
                })
            return Result.success(Unit)
        }
        catch (e: PostgrestRestException){
            return  Result.failure(e)
        }
        catch (e: Exception){
            return  Result.failure(e)
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

    private fun MaterialSection.toData(): NewMaterialSectionDto {
        return NewMaterialSectionDto(
            name = name,
            parentId = parentId,
            disciplineId = disciplineId
        )
    }

    private fun MaterialSection.toDataNull(): NewMaterialSectionDto {
        return NewMaterialSectionDto(
            name = name,
            disciplineId = disciplineId
        )
    }
}