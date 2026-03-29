package pl.mwisniewski.logitrack.orderService.order.infrastructure.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.mwisniewski.logitrack.orderService.order.domain.exception.OrderNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class OrdersExceptionHandler extends RuntimeException {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFoundException(OrderNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
