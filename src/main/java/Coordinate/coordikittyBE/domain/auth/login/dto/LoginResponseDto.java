package Coordinate.coordikittyBE.domain.auth.login.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;

public record LoginResponseDto(
        String email,
        String nickname,
        TokenDto tokenDto
) {
    public static LoginResponseDto of(User user, TokenDto tokenDto) {
        return new LoginResponseDto(user.getEmail(), user.getNickname(), tokenDto);
    }
}
