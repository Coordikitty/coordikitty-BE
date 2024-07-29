package Coordinate.coordikittyBE.domain.auth.login.dto.response;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.auth.login.dto.TokenDto;

public record LoginResponseDto(
        String email,
        String nickname,
        TokenDto tokenDto
) {
    public static LoginResponseDto of(User user, TokenDto tokenDto) {
        return new LoginResponseDto(user.getEmail(), user.getNickname(), tokenDto);
    }
}
