package Coordinate.coordikittyBE.exception;

public record ExceptionResponseDto(String errorCode, String message) {
    public static ExceptionResponseDto of(ErrorType errorType) {
        return new ExceptionResponseDto(errorType.getErrorCode(), errorType.getMessage());
    }
}
