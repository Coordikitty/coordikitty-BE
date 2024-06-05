package Coordinate.coordikittyBE.domain.recommend.dto;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
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
    private List<String> clothImageUrls;
    @JsonProperty("temperature")
    private int temperature;
    @JsonProperty("style")
    private String style;

    public static RecommendRequestDto of(List<String> clothImageUrls, int temperature, String style) {
        return RecommendRequestDto.builder().clothImageUrls(clothImageUrls).temperature(temperature).style(style).build();
    }
}
