package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO {
    private String email;
    private String nickname;
}
