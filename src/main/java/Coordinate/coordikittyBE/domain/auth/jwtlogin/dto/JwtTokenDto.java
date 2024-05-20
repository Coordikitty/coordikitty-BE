package Coordinate.coordikittyBE.domain.auth.jwtlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class JwtTokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
