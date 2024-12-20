package schwarz.it.lws.win.WeatherRepository

import org.springframework.data.jpa.repository.JpaRepository
import schwarz.it.lws.win.model.WeatherData
import java.time.LocalDate

interface WeatherRepository : JpaRepository<WeatherData, Long> {
    fun findByCityAndForecastDateBetween(
        city: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<WeatherData>
}
