
package com.dymbia.sumaqagro.platform.shared.application.error;

/**
 * Defines the common categories of application errors.
 *
 * These error codes are shared across bounded contexts
 * to provide consistent error handling.
 */
public enum ErrorCode {

    /**
     * Indicates that the provided input is invalid.
     */
    VALIDATION_ERROR,

    /**
     * Indicates that the requested resource does not exist.
     */
    NOT_FOUND,

    /**
     * Indicates that the operation conflicts with existing data.
     */
    CONFLICT,

    /**
     * Indicates that authentication is required or has failed.
     */
    UNAUTHORIZED,

    /**
     * Indicates that the user does not have permission
     * to perform the requested operation.
     */
    FORBIDDEN,

    /**
     * Indicates an unexpected application error.
     */
    INTERNAL_ERROR
}
