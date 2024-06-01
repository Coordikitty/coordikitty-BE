package Coordinate.coordikittyBE.domain.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CoordinatesDto {
    private double latitude;     // 위도
    private double longitude;    // 경도

    public static CoordinatesDto fromCoordinates(double latitude, double longitude) {
        return CoordinatesDto.builder().latitude(latitude).longitude(longitude).build();
    }
}
