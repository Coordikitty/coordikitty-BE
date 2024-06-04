package Coordinate.coordikittyBE.domain.recommend.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendRequestDto {
    private List<String> clothImageUrls;
    private int temperature;

    public static RecommendRequestDto of(List<String> clothImageUrls, int temperature) {
        return RecommendRequestDto.builder().clothImageUrls(clothImageUrls).temperature(temperature).build();
    }
}
