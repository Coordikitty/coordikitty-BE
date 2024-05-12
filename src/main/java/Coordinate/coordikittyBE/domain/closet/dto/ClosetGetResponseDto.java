package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
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
}
