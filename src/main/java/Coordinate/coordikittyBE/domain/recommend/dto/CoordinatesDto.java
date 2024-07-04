package Coordinate.coordikittyBE.domain.recommend.dto;



public record CoordinatesDto(double latitude, double longitude) {
    public static CoordinatesDto of(double latitude, double longitude) {
        return new CoordinatesDto(latitude, longitude);
    }
}
