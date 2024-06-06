package Coordinate.coordikittyBE.domain.recommend.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.Category;
import Coordinate.coordikittyBE.domain.closet.enums.Large;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.closet.enums.Thickness;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendRequestDto {
    @JsonProperty("clothImages")
    private String clothImageUrls;
    @JsonProperty("temperature")
    private int temperature;
    @JsonProperty("large")
    private String large;
    @JsonProperty("medium")
    private String medium;
    @JsonProperty("style")
    private String style;
    @JsonProperty("thickness")
    private String thickness;


    public static RecommendRequestDto of(String clothImageUrls, Category.Large large, Category.Medium medium, Style style, Thickness thickness, int temperature) {
        return RecommendRequestDto.builder()
                .clothImageUrls(clothImageUrls)
                .temperature(temperature)
                .large(large.toString())
                .medium(medium.toString())
                .thickness(thickness.toString())
                .style(style.toString()).build();
    }
}
