package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClosetGetResponseDto {
    private UUID clothId;

    private Category.Large large;
    private Category.Medium medium;
    private Category.Small small;

    private Fit fit;
    private Gender gender;
    private Season season;
    private Style style;
    private Thickness thickness;

    private String clothURL;

    public static ClosetGetResponseDto fromCloset(ClothEntity clothEntity) {
        return ClosetGetResponseDto.builder()
                .clothId(clothEntity.getClothId())
                .large(clothEntity.getLarge())
                .medium(clothEntity.getMedium())
                .small(clothEntity.getSmall())
                .fit(clothEntity.getFit())
                .gender(clothEntity.getGender())
                .season(clothEntity.getSeason())
                .style(clothEntity.getStyle())
                .thickness(clothEntity.getThickness())
                .clothURL(clothEntity.getPictureURL())
                .build();
    }
}
