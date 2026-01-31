package com.task_city

import com.task_city.network.remote.CitiesService
import com.task_city.repository.CitiesRepository
import com.task_city.response.CitiesResponse
import com.task_city.response.DataCitiesResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import timber.log.Timber
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class RepositoryTesting {
    private val fakeCities = List(100) {
        DataCitiesResponse(cityOtherName = "Cairo + $it")
    }
    private val citiesService = mockk<CitiesService>()
    private lateinit var repo: CitiesRepository
    private val testDispatcher = StandardTestDispatcher()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setupBeforeTesting() {
        repo = CitiesRepository(citiesService = citiesService)
        Timber.i(message = "setup before testing")
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `return the number of cities`() = runTest {
        // Arrange
        val fakeApi = CitiesResponse(
            dataCitiesResponse = fakeCities
        )
        coEvery { citiesService.getCities(countryID = any()) } returns fakeApi
        val result = repo.getCities(countryID = "60e4482c7cb7d4bc4849c4d5")

        // Assert
        assertEquals(
            expected = 100,
            actual = result?.getOrNull()?.dataCitiesResponse?.size
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDownAfterTesting() {
        Timber.i(message = "tear down after testing")
        Dispatchers.resetMain()
    }
}