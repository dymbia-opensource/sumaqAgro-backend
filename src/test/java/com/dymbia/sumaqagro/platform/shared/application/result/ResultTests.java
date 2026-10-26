
package com.dymbia.sumaqagro.platform.shared.application.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTests {

    @Test
    void shouldCreateSuccessfulResult() {
        Result<String, String> result =
                Result.success("Plot registered");

        assertTrue(result.isSuccess());
        assertFalse(result.isFailure());

        String message = result.fold(
                value -> value,
                error -> error
        );

        assertEquals("Plot registered", message);
    }

    @Test
    void shouldCreateFailedResult() {
        Result<String, String> result =
                Result.failure("Plot already exists");

        assertTrue(result.isFailure());
        assertFalse(result.isSuccess());

        String message = result.fold(
                value -> value,
                error -> error
        );

        assertEquals("Plot already exists", message);
    }
}
