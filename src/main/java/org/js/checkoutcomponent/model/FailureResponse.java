package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FailureResponse {
    @Schema(description = "Type of error, e.g., BAD_REQUEST or NOT_FOUND", example = "BAD_REQUEST, NOT_FOUND")
    private String errorType;

    @Schema(description = "Detailed error message", example = "The provided request is invalid.")
    private String message;

    public static FailureResponse of(String errorType, String message) {
        return new FailureResponse(errorType, message);
    }
}
