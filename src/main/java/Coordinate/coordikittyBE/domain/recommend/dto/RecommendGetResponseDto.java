package Coordinate.coordikittyBE.domain.recommend.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
}
