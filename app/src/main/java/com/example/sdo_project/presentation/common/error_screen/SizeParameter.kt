package com.example.sdo_project.presentation.common.error_screen

sealed class SizeParameter {
    data object SmallSize: SizeParameter()
    data object MediumSize: SizeParameter()
    data object LargeSize: SizeParameter()
}