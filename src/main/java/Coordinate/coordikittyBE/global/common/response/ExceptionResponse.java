package Coordinate.coordikittyBE.global.common.response;

import Coordinate.coordikittyBE.exception.ErrorType;

public record ExceptionResponse(String errorCode, String message) {
    public static ExceptionResponse of(ErrorType errorType) {
        return new ExceptionResponse(errorType.getErrorCode(), errorType.getMessage());
    }
}
