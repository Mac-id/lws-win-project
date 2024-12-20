package schwarz.it.lws.win.WeatherService

<<<<<<< HEAD
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
=======
import org.springframework.stereotype.Service
import org.springframework.cache.annotation.Cacheable
import schwarz.it.lws.win.WeatherRepository.WeatherRepository
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e
import schwarz.it.lws.win.WeatherApi.WeatherApiClient
import schwarz.it.lws.win.WeatherRepository.WeatherRepository
import schwarz.it.lws.win.model.WeatherData
<<<<<<< HEAD
import java.time.LocalDate
=======
import java.time.LocalDateTime
import org.slf4j.LoggerFactory
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e

@Service
class WeatherService(
    private val weatherRepository: WeatherRepository,
    private val weatherApiClient: WeatherApiClient
) {
    private val logger = LoggerFactory.getLogger(WeatherService::class.java)
<<<<<<< HEAD
=======

    @Cacheable("weatherCache")
    fun getWeatherForCity(city: String): List<WeatherData> {
        logger.info("Fetching weather data for city: $city")
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusDays(1)
        var weatherData = weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate)
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e

    fun getForecast(city: String, days: Int): List<WeatherData> {
        logger.info("Fetching forecast for city: $city, days: $days")
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(-1L + days)
        var weatherDatas = weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate)

        if (weatherDatas.size < days) {
            val newWeatherDatas = weatherApiClient.fetchWeatherData(city, days.toInt())
            if (weatherDatas.isEmpty()) {
                weatherRepository.saveAll(newWeatherDatas)
            } else {
                for (weatherData in weatherDatas) {
                    for (newWeatherData in newWeatherDatas) {
                        if (weatherData.forecastDate.isEqual(newWeatherData.forecastDate)) {
                            weatherRepository.save(
                                newWeatherData.copy(id = weatherData.id)
                            )
                        } else {
                            weatherRepository.save(newWeatherData)
                        }
                    }
                }
            }
            weatherDatas = newWeatherDatas
        }

<<<<<<< HEAD
        return weatherDatas
=======
        return weatherData
    }

    @Cacheable("forecastCache")
    fun getForecast(city: String, days: Long): List<WeatherData> {
        logger.info("Fetching forecast for city: $city, days: $days")
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusDays(days)
        var weatherData = weatherRepository.findByCityAndForecastDateBetween(city, startDate, endDate)

        if (weatherData.size < days) {
            weatherData = weatherApiClient.fetchWeatherData(city, days.toInt())
            weatherRepository.saveAll(weatherData)
        }

        return weatherData
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e
    }
}

