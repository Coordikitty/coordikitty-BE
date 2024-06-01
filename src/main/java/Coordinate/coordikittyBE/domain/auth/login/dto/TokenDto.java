package Coordinate.coordikittyBE.domain.auth.login.dto;



public record TokenDto (String accessToken, String refreshToken, String nickname){
    public static TokenDto of(String accessToken, String refreshToken) {
        return new TokenDto(accessToken, refreshToken, null);
    }

    public TokenDto addNickname(String nickname){
        return new TokenDto(accessToken, refreshToken, nickname);
    }
}