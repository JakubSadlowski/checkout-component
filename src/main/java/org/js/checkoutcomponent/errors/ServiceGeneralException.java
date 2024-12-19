package org.js.checkoutcomponent.errors;

public class ServiceGeneralException extends RuntimeException {
    public ServiceGeneralException(String message, Exception cause) {
        super(message, cause);
    }

    public ServiceGeneralException(String message) {
        super(message);
    }
}
