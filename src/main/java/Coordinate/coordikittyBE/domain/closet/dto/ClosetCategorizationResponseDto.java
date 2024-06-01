package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClosetCategorizationResponseDto {
    private Category.Large large;
    private Category.Medium medium;
    private Category.Small small;

    private Fit fit;
    private Gender gender;
//    private Season season;
    private Style style;
//    private Thickness thickness;

//    public static ClosetCategorizationResponseDto fromDL() {
//        return ClosetCategorizationResponseDto.builder()
//                .large()
//                .medium()
//                .small()
//                .fit()
//                .gender()
//                .style()
//                .build();
//    }
}
