package com.example.sdo_project.presentation

sealed class MainEvent {
    data object Recheck: MainEvent()
}