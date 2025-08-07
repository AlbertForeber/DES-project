package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection

interface MaterialRepository {
    suspend fun getMaterials(parent: MaterialSection): Result<List<Material>>           // Материалы в текущей секции (раздел, подраздел)
    suspend fun addMaterial(material: Material): Result<Unit>                           // Добавление материала (дата-класс содержит все необходимое)
    suspend fun getMaterialSections(disciplineId: Int): Result<List<MaterialSection>>   // Получаем относительно id дисциплины
    suspend fun getMaterialSections(parent: MaterialSection): Result<List<MaterialSection>>   // Получаем относительно id дисциплины
    suspend fun addMaterialSection (section: MaterialSection) : Result<Unit>            // Добавление директории (дата-класс содержит все необходимое)
    suspend fun addMaterialSections (sections: List<MaterialSection>, initialParent: Int? = null): Result<Unit>
}