package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtTokenRequestDto {
    private String refreshToken;
}
