package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;

public record ClosetPostRequestDto(
        Category.Large large,
        Category.Medium medium,
        Category.Small small,
        Fit fit,
        Gender gender,
        Style style,
        Thickness thickness) {
}
