package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClosetPostRequestDto {
    private Category.Large large;
    private Category.Medium medium;
    private Category.Small small;
    private Fit fit;
    private Gender gender;
    private Style style;
    private Thickness thickness;

}
