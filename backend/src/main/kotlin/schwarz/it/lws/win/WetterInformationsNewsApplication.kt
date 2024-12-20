package schwarz.it.lws.win

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import schwarz.it.lws.win.WeatherApi.WeatherApiClient

@SpringBootApplication
class WetterInformationsNewsApplication {
    @Bean
    fun commandLineRunner(weatherApiClient: WeatherApiClient): CommandLineRunner {
        return CommandLineRunner {
            weatherApiClient.fetchWeatherData("Heilbronn", 3)
        }
    }

}
<<<<<<< HEAD
=======


fun main(args: Array<String>) {
    runApplication<WetterInformationsNewsApplication>(*args)
}
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e


fun main(args: Array<String>) {
    runApplication<WetterInformationsNewsApplication>(*args)
}
