package Coordinate.coordikittyBE.domain.closet.dto.request;

import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.enums.*;
import Coordinate.coordikittyBE.domain.user.entity.User;

public record ClosetPostRequestDto(
        Category.Large large,
        Category.Medium medium,
        Category.Small small,
        Fit fit,
        Gender gender,
        Style style,
        Thickness thickness)
{
    public static Cloth of(ClosetPostRequestDto closetPostRequestDto, User user){
        return Cloth.builder()
                .user(user)
                .large(closetPostRequestDto.large())
                .medium(closetPostRequestDto.medium())
                .small(closetPostRequestDto.small())
                .fit(closetPostRequestDto.fit())
                .gender(closetPostRequestDto.gender())
                .style(closetPostRequestDto.style())
                .thickness(closetPostRequestDto.thickness())
                .build();
    }
}
