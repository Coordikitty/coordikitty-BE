package Coordinate.coordikittyBE.domain.auth.login.dto.response;


public record LoginInfoResponseDto (
        String email,
        String nickname,
        String accessToken
){
    public static LoginInfoResponseDto of(LoginResponseDto loginResponseDto) {
        return new LoginInfoResponseDto(loginResponseDto.email(), loginResponseDto.nickname(), loginResponseDto.accessToken());
    }
}
