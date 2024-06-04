package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignUpSocialRequestDto {
    private String email;

    public static User toEntity(SignUpSocialRequestDto signUpSocialRequestDto) {
        return User.builder()
                .email(signUpSocialRequestDto.getEmail())
                .password(UUID.randomUUID().toString())
                .build();
    }
}
