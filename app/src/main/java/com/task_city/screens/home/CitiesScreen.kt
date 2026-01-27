package com.task_city.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.task_city.view_model.CitiesViewModel

object CitiesScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<CitiesViewModel>()
        Cities(citiesViewModel = viewModel)
    }
}