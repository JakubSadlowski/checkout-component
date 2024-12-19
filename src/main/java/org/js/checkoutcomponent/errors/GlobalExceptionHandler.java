package org.js.checkoutcomponent.errors;

import lombok.extern.apachecommons.CommonsLog;
import org.js.checkoutcomponent.model.FailureCartItemNotFoundResponse;
import org.js.checkoutcomponent.model.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
@CommonsLog
public class GlobalExceptionHandler {

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<FailureCartItemNotFoundResponse> handleCartItemNotFoundException(CartItemNotFoundException ex, WebRequest request) {
        FailureCartItemNotFoundResponse response = FailureCartItemNotFoundResponse.of("NOT_FOUND", ex.getMessage());
        log.warn("Handled CartItemNotFoundException: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<FailureResponse> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
        FailureResponse response = FailureResponse.of("BAD_REQUEST", ex.getMessage());
        log.warn("Handled InvalidRequestException: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(ServiceGeneralException.class)
    public ResponseEntity<FailureResponse> handleServiceGeneralException(ServiceGeneralException ex, WebRequest request) {
        FailureResponse response = FailureResponse.of("INTERNAL_SERVER_ERROR", "An unexpected error occurred: " + ex.getMessage());
        log.error("Handled ServiceGeneralException: " + ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(CartItemWithZeroOrLessQuantity.class)
    public ResponseEntity<FailureResponse> handleCartItemWithZeroOrLessQuantity(CartItemWithZeroOrLessQuantity ex, WebRequest request) {
        FailureResponse response = FailureResponse.of("BAD_REQUEST", ex.getMessage());
        log.warn("Handled CartItemWithZeroOrLessQuantity: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}
