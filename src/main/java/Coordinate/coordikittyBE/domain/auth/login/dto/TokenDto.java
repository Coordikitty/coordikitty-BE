package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}