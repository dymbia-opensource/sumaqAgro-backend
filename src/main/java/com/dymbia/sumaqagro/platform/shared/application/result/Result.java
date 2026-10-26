package com.dymbia.sumaqagro.platform.shared.application.result;

import java.util.function.Function;

/**
 * Represents the outcome of an operation that can either
 * succeed with a value or fail with an error.
 *
 * @param <T> the type of the successful result
 * @param <E> the type of the error
 */
public sealed interface Result<T, E> permits Result.Success, Result.Failure {

    /**
     * Represents a successful operation result.
     *
     * @param value the successful result value
     * @param <T> the type of the successful result
     * @param <E> the type of the error
     */
    record Success<T, E>(T value) implements Result<T, E> {
    }

    /**
     * Represents a failed operation result.
     *
     * @param error the error associated with the failure
     * @param <T> the type of the successful result
     * @param <E> the type of the error
     */
    record Failure<T, E>(E error) implements Result<T, E> {
    }

    /**
     * Creates a successful result containing the given value.
     *
     * @param value the successful result value
     * @param <T> the type of the successful result
     * @param <E> the type of the error
     * @return a successful result
     */
    static <T, E> Result<T, E> success(T value) {
        return new Success<>(value);
    }

    /**
     * Creates a failed result containing the given error.
     *
     * @param error the error associated with the failure
     * @param <T> the type of the successful result
     * @param <E> the type of the error
     * @return a failed result
     */
    static <T, E> Result<T, E> failure(E error) {
        return new Failure<>(error);
    }

    /**
     * Checks whether this result represents a successful operation.
     *
     * @return true if the result is successful, false otherwise
     */
    default boolean isSuccess() {
        return this instanceof Success<?, ?>;
    }

    /**
     * Checks whether this result represents a failed operation.
     *
     * @return true if the result is a failure, false otherwise
     */
    default boolean isFailure() {
        return this instanceof Failure<?, ?>;
    }

    /**
     * Transforms this result into a value by applying the
     * corresponding function based on its outcome.
     *
     * @param onSuccess the function applied to a successful result
     * @param onFailure the function applied to a failed result
     * @param <R> the type of the transformed result
     * @return the value produced by the corresponding function
     */
    default <R> R fold(
            Function<? super T, ? extends R> onSuccess,
            Function<? super E, ? extends R> onFailure) {

        return switch (this) {
            case Success<T, E> success ->
                    onSuccess.apply(success.value());

            case Failure<T, E> failure ->
                    onFailure.apply(failure.error());
        };
    }
}
