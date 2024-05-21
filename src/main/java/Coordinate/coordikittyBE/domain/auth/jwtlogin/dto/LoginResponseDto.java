package Coordinate.coordikittyBE.domain.auth.jwtlogin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDto {
    private String email;
    private String nickname;
}
