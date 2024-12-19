package org.js.checkoutcomponent.errors;

public class InvalidRequestException extends ServiceGeneralException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
