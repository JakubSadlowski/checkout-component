package org.js.checkoutcomponent.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_ERROR = "error";

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<Object> handleCartItemNotFoundException(CartItemNotFoundException ex, WebRequest request) {
        Map<String, Object> body = Map.of(KEY_MESSAGE, ex.getMessage(), KEY_TIMESTAMP, System.currentTimeMillis());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
        Map<String, Object> body = Map.of(KEY_MESSAGE, ex.getMessage(), KEY_TIMESTAMP, System.currentTimeMillis());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceGeneralException.class)
    public ResponseEntity<Object> handleGenericException(ServiceGeneralException ex, WebRequest request) {
        Map<String, Object> body = Map.of(KEY_MESSAGE, "An unexpected error occurred", KEY_ERROR, ex.getMessage(), KEY_TIMESTAMP, System.currentTimeMillis());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CartItemWithZeroOrLessQuantity.class)
    public ResponseEntity<Object> handleCartItemWithZeroOrLessQuantity(CartItemWithZeroOrLessQuantity ex, WebRequest request) {
        Map<String, Object> body = Map.of(KEY_MESSAGE, ex.getMessage(), KEY_TIMESTAMP, System.currentTimeMillis());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
