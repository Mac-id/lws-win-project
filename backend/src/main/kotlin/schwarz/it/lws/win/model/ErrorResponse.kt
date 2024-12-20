package schwarz.it.lws.win.model

data class ErrorResponse(
    val status: Int,
    val message: String,
    val timestamp: String
)
