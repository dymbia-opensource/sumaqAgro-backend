package com.dymbia.sumaqagro.platform.shared.application.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ApplicationError record.
 */
class ApplicationErrorTests {

    /**
     * Verifies that a validation error is created correctly.
     */
    @Test
    void shouldCreateValidationError() {
        ApplicationError error =
                ApplicationError.validation("Invalid plot name");

        assertEquals(ErrorCode.VALIDATION_ERROR, error.code());
        assertEquals("Invalid plot name", error.message());
    }

    /**
     * Verifies that a conflict error is created correctly.
     */
    @Test
    void shouldCreateConflictError() {
        ApplicationError error =
                ApplicationError.conflict("Plot already exists");

        assertEquals(ErrorCode.CONFLICT, error.code());
        assertEquals("Plot already exists", error.message());
    }

    /**
     * Verifies that blank error messages are rejected.
     */
    @Test
    void shouldRejectBlankMessage() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ApplicationError.validation(" ")
        );
    }
}
