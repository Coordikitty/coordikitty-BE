package Coordinate.coordikittyBE.domain.auth.login.dto;


public record LogoutRequestDto(String email, String refreshToken) {

    public static LogoutRequestDto toDto(String email, String refreshToken) {
        return new LogoutRequestDto(email, refreshToken);
    }
}
