package com.dymbia.sumaqagro.platform.shared.interfaces.rest.advice;

import com.dymbia.sumaqagro.platform.shared.application.error.ApplicationError;
import org.springframework.http.HttpStatus;

/**
 * Maps application error categories to HTTP status codes.
 */
public final class ApplicationErrorHttpMapper {

    private ApplicationErrorHttpMapper() {
    }

    /**
     * Resolves the HTTP status associated with an application error.
     *
     * @param error the application error
     * @return the corresponding HTTP status
     */
    public static HttpStatus toHttpStatus(ApplicationError error) {
        return switch (error.code()) {
            case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
