package Coordinate.coordikittyBE.global.common.response;

public record SuccessResponse<T>(T response) {
    public static <T> SuccessResponse<T> from(T response) {
        return new SuccessResponse<>(response);
    }
}
