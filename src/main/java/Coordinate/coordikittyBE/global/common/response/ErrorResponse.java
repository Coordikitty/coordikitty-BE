package Coordinate.coordikittyBE.global.common.response;

import Coordinate.coordikittyBE.exception.ErrorType;

public record ErrorResponse(String errorCode, String message) {
    public static ErrorResponse of(ErrorType errorType) {
        return new ErrorResponse(errorType.getErrorCode(), errorType.getMessage());
    }
}
