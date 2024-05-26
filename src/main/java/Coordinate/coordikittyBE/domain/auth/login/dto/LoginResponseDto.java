package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
}
