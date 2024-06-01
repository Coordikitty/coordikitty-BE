package Coordinate.coordikittyBE.domain.recommend.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendGetResponseDto {
    private Category.Large large;
    private Category.Medium medium;
    private Category.Small small;

    private Fit fit;
    private Gender gender;
    private Season season;
    private Style style;
    private Thickness thickness;

    private String clothURL;

    public static RecommendGetResponseDto fromCloth(Cloth cloth) {
        return RecommendGetResponseDto.builder()
                .large(cloth.getLarge())
                .medium(cloth.getMedium())
                .small(cloth.getSmall())
                .fit(cloth.getFit())
                .gender(cloth.getGender())
                .season(cloth.getSeason())
                .style(cloth.getStyle())
                .thickness(cloth.getThickness())
                .clothURL(cloth.getPictureURL())
                .build();
    }
}
