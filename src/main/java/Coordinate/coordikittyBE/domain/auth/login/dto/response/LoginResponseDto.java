package Coordinate.coordikittyBE.domain.auth.login.dto.response;

import Coordinate.coordikittyBE.domain.user.entity.User;

public record LoginResponseDto(
        String email,
        String nickname,
        String accessToken,
        String refreshToken
) {
    public static LoginResponseDto of(User user, String accessToken, String refreshToken) {
        return new LoginResponseDto(user.getEmail(), user.getNickname(), accessToken, refreshToken);
    }
}
