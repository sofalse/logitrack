package pl.mwisniewski.logitrack.orderService.cargo.infrastructure.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.mwisniewski.logitrack.orderService.cargo.domain.exception.CargoNotFoundException;

import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class CargosExceptionHandler {

    @ExceptionHandler(CargoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCargoNotFound(CargoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonParseError(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Invalid request body: " + ex.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (!UUID.class.equals(ex.getRequiredType())) {
            throw ex;
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Invalid UUID format: '%s'".formatted(ex.getValue())));
    }
}
