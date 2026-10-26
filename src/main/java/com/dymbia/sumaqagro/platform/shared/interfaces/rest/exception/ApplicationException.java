package com.dymbia.sumaqagro.platform.shared.interfaces.rest.exception;

import com.dymbia.sumaqagro.platform.shared.application.error.ApplicationError;

import java.util.Objects;

/**
 * Represents a known application failure that must be
 * translated into an HTTP error response.
 */
public class ApplicationException extends RuntimeException {

    private final ApplicationError error;

    /**
     * Creates an application exception.
     *
     * @param error the structured application error
     */
    public ApplicationException(ApplicationError error) {
        super(Objects.requireNonNull(error, "Error is required").message());
        this.error = error;
    }

    /**
     * Returns the structured application error.
     *
     * @return the application error
     */
    public ApplicationError getError() {
        return error;
    }
}
