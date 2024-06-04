package Coordinate.coordikittyBE.domain.auth.login.dto;

public record LoginResponseDto(
        String email,
        String nickname,
        TokenDto tokenDto
) {
    public static LoginResponseDto of(String email, String nickname, TokenDto tokenDto) {
        return new LoginResponseDto(email, nickname, tokenDto);
    }
}
