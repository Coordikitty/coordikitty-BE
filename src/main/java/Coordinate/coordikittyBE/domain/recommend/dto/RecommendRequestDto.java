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
    private List<Cloth> clothes;
    private int temperature;

    public static RecommendRequestDto from(List<Cloth> clothes, int temperature) {
        return RecommendRequestDto.builder().clothes(clothes).temperature(temperature).build();
    }
}
