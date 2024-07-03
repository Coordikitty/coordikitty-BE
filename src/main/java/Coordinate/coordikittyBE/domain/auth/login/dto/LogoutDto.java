package Coordinate.coordikittyBE.domain.auth.login.dto;


public record LogoutDto(String email, String refreshToken) {

    public static LogoutDto of(String email, String refreshToken) {
        return new LogoutDto(email, refreshToken);
    }
}
