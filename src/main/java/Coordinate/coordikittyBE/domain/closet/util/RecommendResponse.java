package Coordinate.coordikittyBE.domain.closet.util;

import Coordinate.coordikittyBE.domain.closet.enums.Category;
import Coordinate.coordikittyBE.domain.closet.enums.Fit;
import Coordinate.coordikittyBE.domain.closet.enums.Gender;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendResponse {
    @JsonProperty("large")
    private Category.Large large;
    @JsonProperty("medium")
    private Category.Medium medium;
    @JsonProperty("small")
    private Category.Small small;

    @JsonProperty("fit")
    private Fit fit;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("style")
    private Style style;
}
