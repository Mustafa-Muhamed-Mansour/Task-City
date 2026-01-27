package com.task_city.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.task_city.response.DistrictModel
import com.task_city.utils.Constant.ID
import com.task_city.view_model.CitiesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cities(citiesViewModel: CitiesViewModel) {

    val uiState by citiesViewModel.uiState.collectAsStateWithLifecycle()
    val snackBar = remember { SnackbarHostState() }
    var selectedCityId by remember { mutableStateOf<String?>(value = null) }
    var searchOfCity by remember { mutableStateOf(value = "") }

    LaunchedEffect(Unit) {
        citiesViewModel.fetchAllCities(countryID = ID)
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBar) }) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close of choose delivery area",
                    tint = Color.Black
                )

                Text(
                    text = "Choose the delivery area",
                    color = Color.Black,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            SearchBar(
                query = searchOfCity,
                onQueryChange = { searchOfCity = it },
                onSearch = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(
                        text = "City / Area",
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                },
                trailingIcon = {},
                active = true,
                onActiveChange = {},
                colors = SearchBarDefaults.colors(
                    containerColor = Color.White
                ),
                content = {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 8.dp)
                            .verticalScroll(state = rememberScrollState())
                    ) {
                        if (uiState.isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }
                        }

                        if (uiState.error.isNotBlank()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = uiState.error,
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            uiState?.data?.let { response ->
                                response?.dataCitiesResponse?.forEach { city ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        IconButton(
                                            onClick = {
                                                selectedCityId =
                                                    if (selectedCityId == city.cityId) null else city.cityId
                                            }) {
                                            Icon(
                                                imageVector = if (selectedCityId == city.cityId) Icons.Default.KeyboardArrowUp
                                                else Icons.Default.KeyboardArrowDown,
                                                contentDescription = "Expand city",
                                                tint = Color.Gray
                                            )
                                        }
                                        Text(
                                            text = city.cityName,
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                    if (selectedCityId == city.cityId) {
                                        city.districtModel.forEach { district ->
                                            DistrictItem(district)
                                        }
                                    }
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                })
        }
    }
}


@Composable
fun DistrictItem(districtModel: DistrictModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F6F8))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween.also {
            Arrangement.Center
        }
    ) {
        Text(
            text = "${districtModel.zoneName} - ${districtModel.districtName}",
            fontSize = 13.sp,
            color = if (districtModel.pickupAvailability) Color.Black else Color.Gray
        )

        if (!districtModel.pickupAvailability && !districtModel.dropOffAvailability) {
            Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(
                    text = "Uncovered",
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}