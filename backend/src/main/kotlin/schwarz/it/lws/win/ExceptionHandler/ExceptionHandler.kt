package schwarz.it.lws.win.ExceptionHandler

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import schwarz.it.lws.win.model.ErrorResponse
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = ex.message ?: "Unknown error",
                    timestamp = LocalDateTime.now().toString()
                )
            )
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleException(ex: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = ex.message ?: "Unknown error",
                    timestamp = LocalDateTime.now().toString()
                )
            )
    }
}
