package Coordinate.coordikittyBE.domain.closet.dto.response;

import Coordinate.coordikittyBE.domain.closet.enums.*;
import Coordinate.coordikittyBE.domain.closet.util.CategorizedResponse;


public record ClosetCategorizationResponseDto(
        Category.Large large,
        Category.Medium medium,
        Category.Small small,
        Fit fit,
        Gender gender,
        Style style
        //Season season,
        //Thickness thickness
) {

    public static ClosetCategorizationResponseDto fromDL(CategorizedResponse response) {
        return new ClosetCategorizationResponseDto(
                response.getLarge(),
                response.getMedium(),
                response.getSmall(),
                response.getFit(),
                response.getGender(),
                response.getStyle()
        );
    }
}
