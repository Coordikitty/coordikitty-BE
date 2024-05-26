package Coordinate.coordikittyBE.domain.auth.jwtlogin.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtTokenRequestDto {
    private String refreshToken;
}
