package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FailureCartItemNotFoundResponse {
    @Schema(description = "Type of error, e.g., BAD_REQUEST or NOT_FOUND", example = "NOT_FOUND")
    private String errorType;

    @Schema(description = "Cart item not found in database.", example = "Item X not found.")
    private String message;

    public static FailureCartItemNotFoundResponse of(String errorType, String message) {
        return new FailureCartItemNotFoundResponse(errorType, message);
    }
}
