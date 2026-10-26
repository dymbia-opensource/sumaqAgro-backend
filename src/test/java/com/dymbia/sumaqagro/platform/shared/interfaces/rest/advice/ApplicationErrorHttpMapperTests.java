package com.dymbia.sumaqagro.platform.shared.interfaces.rest.advice;

import com.dymbia.sumaqagro.platform.shared.application.error.ApplicationError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for application error to HTTP status mapping.
 */
class ApplicationErrorHttpMapperTests {

    /**
     * Verifies that validation errors map to HTTP 400.
     */
    @Test
    void shouldMapValidationErrorToBadRequest() {
        var error = ApplicationError.validation("Invalid input");

        assertEquals(
                HttpStatus.BAD_REQUEST,
                ApplicationErrorHttpMapper.toHttpStatus(error)
        );
    }

    /**
     * Verifies that missing resources map to HTTP 404.
     */
    @Test
    void shouldMapNotFoundErrorToNotFound() {
        var error = ApplicationError.notFound("Resource not found");

        assertEquals(
                HttpStatus.NOT_FOUND,
                ApplicationErrorHttpMapper.toHttpStatus(error)
        );
    }

    /**
     * Verifies that conflicts map to HTTP 409.
     */
    @Test
    void shouldMapConflictErrorToConflict() {
        var error = ApplicationError.conflict("Resource already exists");

        assertEquals(
                HttpStatus.CONFLICT,
                ApplicationErrorHttpMapper.toHttpStatus(error)
        );
    }
}
