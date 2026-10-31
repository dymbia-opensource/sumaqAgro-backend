package com.dymbia.sumaqagro.platform.shared.interfaces.rest.advice;

import com.dymbia.sumaqagro.platform.shared.interfaces.rest.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    /**
     * Handles validation errors thrown by commands, queries and value objects.
     * The exception message is a key from messages.properties.
     *
     * @param exception the validation exception
     * @param request the current HTTP request
     * @return a 400 Bad Request response with the translated message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        String messageKey = exception.getMessage();
        String detail = messageSource.getMessage(
                messageKey, null, messageKey, LocaleContextHolder.getLocale()
        );

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                detail
        );

        problem.setInstance(
                java.net.URI.create(request.getRequestURI())
        );

        return ResponseEntity.badRequest().body(problem);
    }
}
