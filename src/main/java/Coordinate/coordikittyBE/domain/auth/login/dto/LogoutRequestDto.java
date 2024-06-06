package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequestDto {
    private String email;
    private String refreshToken;

    public static LogoutRequestDto toDto(String email, String refreshToken) {
        return LogoutRequestDto.builder().email(email).refreshToken(refreshToken).build();
    }
}
