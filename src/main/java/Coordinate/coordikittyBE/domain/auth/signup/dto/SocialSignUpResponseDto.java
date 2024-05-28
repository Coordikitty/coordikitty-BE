package Coordinate.coordikittyBE.domain.auth.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SocialSignUpResponseDto {
    private String email;
    private String accessToken;
}
