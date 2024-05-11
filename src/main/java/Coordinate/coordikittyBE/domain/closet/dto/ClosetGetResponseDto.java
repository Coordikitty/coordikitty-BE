package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;

import java.util.UUID;

public class ClosetGetResponseDto {
    private UUID clothId;

    private Category large;
    private Category medium;
    private Category small;

    private Fit fit;
    private Gender gender;
    private Season season;
    private Style style;
    private Thickness thickness;

    private String clothURL;
}
