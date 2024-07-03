package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.Builder;

import java.util.UUID;

public record ClosetGetResponseDto(
        UUID clothId,
        Category.Large large,
        Category.Medium medium,
        Category.Small small,
        Fit fit,
        Gender gender,
        Style style,
        Thickness thickness,
        String imageUrl
) {
    public static ClosetGetResponseDto fromEntity(Cloth cloth) {
        return new ClosetGetResponseDto(
                cloth.getId(),
                cloth.getLarge(),
                cloth.getMedium(),
                cloth.getSmall(),
                cloth.getFit(),
                cloth.getGender(),
                cloth.getStyle(),
                cloth.getThickness(),
                cloth.getImageUrl()
        );
    }
}
