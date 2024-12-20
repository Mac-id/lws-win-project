package schwarz.it.lws.win.ExceptionHandler

<<<<<<< HEAD
class WeatherApiException(message: String) : RuntimeException(message)
=======
class WeatherApiException(message: String, cause: Throwable?) : RuntimeException(message, cause)
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e
