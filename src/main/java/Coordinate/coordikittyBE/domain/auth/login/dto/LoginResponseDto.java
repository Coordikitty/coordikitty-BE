package Coordinate.coordikittyBE.domain.auth.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private String email;
    private String nickname;
    private TokenDto tokenDto;
}
