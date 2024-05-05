package Coordinate.coordikittyBE.domain.closet.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Fit;
import Coordinate.coordikittyBE.domain.closet.enums.Gender;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClosetRegisterRequestDTO {
    private String clothUrl;
    private Style style;
    private Situation situation;
    private Fit fit;
    private Gender gender;
}
