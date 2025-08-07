package com.example.sdo_project.presentation.add_material

import java.lang.reflect.Type

sealed class TypeAddingItem {
    object Material : TypeAddingItem()
    object Section: TypeAddingItem()
    object ManySections: TypeAddingItem()
}

fun getTypeAddingItemsMap ():Map<String, TypeAddingItem>{
    return mapOf(
        "Директория" to TypeAddingItem.Section,
        "Несколько Директорий" to TypeAddingItem.ManySections,
        "Материал" to TypeAddingItem.Material
    )
}