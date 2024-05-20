package Coordinate.coordikittyBE.domain.auth.jwtlogin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO {
    private String email;
    private String nickname;
}
