package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;
import Coordinate.coordikittyBE.domain.closet.util.RecommendResponse;
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

    public static ClosetCategorizationResponseDto fromDL(RecommendResponse response) {
        return ClosetCategorizationResponseDto.builder()
                .large(response.getLarge())
                .medium(response.getMedium())
                .small(response.getSmall())
                .fit(response.getFit())
                .gender(response.getGender())
                .style(response.getStyle())
                .build();
    }
}
