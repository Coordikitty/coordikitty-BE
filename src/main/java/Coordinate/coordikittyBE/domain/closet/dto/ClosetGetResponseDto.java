package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile clothImg;

    public static ClosetGetResponseDto fromCloset(Cloth cloth) {
        return ClosetGetResponseDto.builder()
                .clothId(cloth.getId())
                .large(cloth.getLarge())
                .medium(cloth.getMedium())
                .small(cloth.getSmall())
                .fit(cloth.getFit())
                .gender(cloth.getGender())
                .style(cloth.getStyle())
                .thickness(cloth.getThickness())
                .build();
    }
}
