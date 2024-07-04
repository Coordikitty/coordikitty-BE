package Coordinate.coordikittyBE.domain.recommend.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.Category;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.closet.enums.Thickness;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecommendRequestDto(
        @JsonProperty("clothImages") String clothImageUrls,
        @JsonProperty("temperature") int temperature,
        @JsonProperty("large") Category.Large large,
        @JsonProperty("medium") Category.Medium medium,
        @JsonProperty("style") Style style,
        @JsonProperty("thickness") Thickness thickness
) {
    public static RecommendRequestDto of(Cloth cloth, int temperature)
    {
        return new RecommendRequestDto(
                cloth.getImageUrl(),
                temperature,
                cloth.getLarge(),
                cloth.getMedium(),
                cloth.getStyle(),
                cloth.getThickness()
                );
    }
}
