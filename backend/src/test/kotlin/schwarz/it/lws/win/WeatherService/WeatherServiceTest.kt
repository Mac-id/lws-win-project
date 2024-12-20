package schwarz.it.lws.win.WeatherService

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import schwarz.it.lws.win.WeatherApi.WeatherApiClient
import schwarz.it.lws.win.WeatherRepository.WeatherRepository
import schwarz.it.lws.win.model.WeatherData
import java.time.LocalDate
import java.time.LocalDateTime

class WeatherServiceTest : FunSpec({

    val weatherRepository = mockk<WeatherRepository>(relaxed = true)
    val weatherApiClient = mockk<WeatherApiClient>(relaxed = true)
    val weatherService = WeatherService(weatherRepository, weatherApiClient)

    test("should get weather forecast from repository if available") {
        val city = "Heilbronn"
        val days = 3
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(-1L + days)

        val expectedWeatherData = List(days.toInt()) {
            WeatherData(
                id = it.toLong(),
                city = city,
                forecastDate = startDate.plusDays(it.toLong()),
                temperature = 20.0,
                minTemperature = 15.0,
                maxTemperature = 25.0,
                description = "Sunny",
                iconCode = "01d",
                createdAt = LocalDateTime.now(),
                humidity = 50
            )
        }

        every {
            weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate)
        } returns expectedWeatherData

        val forecast = weatherService.getForecast(city, days)

        forecast shouldBe expectedWeatherData
        verify(exactly = 1) { weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate) }
        verify(exactly = 0) { weatherApiClient.fetchWeatherData(any(), any()) }
    }

    test("should fetch weather forecast from API when not in repository") {
        val city = "Stuttgart"
        val days = 5
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(-1L + days)

        val apiWeatherData = List(days.toInt()) {
            WeatherData(
                id = it.toLong(),
                city = city,
                forecastDate = startDate.plusDays(it.toLong()),
                temperature = 18.0,
                minTemperature = 13.0,
                maxTemperature = 23.0,
                description = "Cloudy",
                iconCode = "03d",
                createdAt = LocalDateTime.now(),
                humidity = 60
            )
        }

        every {
            weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate)
        } returns emptyList()

        every { weatherApiClient.fetchWeatherData(city, days) } returns apiWeatherData

        val forecast = weatherService.getForecast(city, days)

        forecast shouldBe apiWeatherData
        verify(exactly = 1) { weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate) }
        verify(exactly = 1) { weatherApiClient.fetchWeatherData(city, days) }
        verify(exactly = 1) { weatherRepository.saveAll(apiWeatherData) }
    }
})
