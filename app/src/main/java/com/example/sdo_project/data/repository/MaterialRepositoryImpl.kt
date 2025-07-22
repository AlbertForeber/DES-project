package com.example.sdo_project.data.repository

import com.example.sdo_project.data.remote.dto.MaterialDto
import com.example.sdo_project.data.remote.dto.MaterialSectionDto
import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

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
                }.decodeList<Material>()

            return Result.success(result)

        }catch (e:Exception){
            return Result.failure(e)
        }
    }

    override suspend fun addMaterial(material: Material): Result<Unit> {
        try {
            client.from("material").insert(material)
            return Result.success(Unit)
        }catch (e: Exception){
           return  Result.failure(e)
        }
    }

    override suspend fun getMaterialSections(disciplineId: Int, parentId: Int?): Result<List<MaterialSection>> {
        try {
            val result = client.from("material_section")
                .select {
                    filter {
                        eq ("discipline_id", disciplineId)
                        if (parentId != null) {
                            eq("parent_id", parentId)
                        }
                    }
                }.decodeList<MaterialSection>()
            return Result.success(result)
        }
        catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun MaterialDto.toDomain(): Material{
        return Material(
            id  = id,
            sectionId = sectionId,
            accessTime = accessTime,
            content = content,
            name = name
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