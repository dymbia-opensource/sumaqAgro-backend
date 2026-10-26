package com.dymbia.sumaqagro.platform.shared.interfaces.rest.advice;

import com.dymbia.sumaqagro.platform.shared.interfaces.rest.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Provides centralized exception handling for REST controllers.
 *
 * Converts known application exceptions into standardized
 * HTTP Problem Details responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles known application exceptions.
     *
     * @param exception the application exception
     * @param request the current HTTP request
     * @return a standardized HTTP error response
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ProblemDetail> handleApplicationException(
            ApplicationException exception,
            HttpServletRequest request) {

        HttpStatus status =
                ApplicationErrorHttpMapper.toHttpStatus(
                        exception.getError()
                );

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                exception.getError().message()
        );

        problem.setInstance(
                java.net.URI.create(request.getRequestURI())
        );

        return ResponseEntity.status(status).body(problem);
    }
}
