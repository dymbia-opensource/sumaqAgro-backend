package com.dymbia.sumaqagro.platform.shared.application.error;

import java.util.Objects;

/**
 * Represents a structured application error.
 *
 * Contains an error code and a descriptive message
 * that explains why an operation failed.
 *
 * @param code the category of the application error
 * @param message the descriptive error message
 */
public record ApplicationError(
        ErrorCode code,
        String message
) {

    /**
     * Creates an application error and validates its properties.
     *
     * @param code the error category
     * @param message the descriptive error message
     * @throws NullPointerException if code or message is null
     * @throws IllegalArgumentException if message is blank
     */
    public ApplicationError {
        Objects.requireNonNull(code, "Error code is required");
        Objects.requireNonNull(message, "Error message is required");

        if (message.isBlank()) {
            throw new IllegalArgumentException(
                    "Error message cannot be blank"
            );
        }
    }

    /**
     * Creates a validation error.
     *
     * @param message the validation error message
     * @return an application error with VALIDATION_ERROR code
     */
    public static ApplicationError validation(String message) {
        return new ApplicationError(
                ErrorCode.VALIDATION_ERROR,
                message
        );
    }

    /**
     * Creates a resource-not-found error.
     *
     * @param message the resource-not-found message
     * @return an application error with NOT_FOUND code
     */
    public static ApplicationError notFound(String message) {
        return new ApplicationError(
                ErrorCode.NOT_FOUND,
                message
        );
    }

    /**
     * Creates a conflict error.
     *
     * @param message the conflict error message
     * @return an application error with CONFLICT code
     */
    public static ApplicationError conflict(String message) {
        return new ApplicationError(
                ErrorCode.CONFLICT,
                message
        );
    }
}
