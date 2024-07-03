package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import java.util.UUID;

public record SignUpSocialRequestDto(String email) {

    public static User fromEntity(SignUpSocialRequestDto signUpSocialRequestDto) {
        return User.builder()
                .email(signUpSocialRequestDto.email())
                .password(UUID.randomUUID().toString())
                .build();
    }

    public static SignUpSocialRequestDto from(String email) {
        return new SignUpSocialRequestDto(email);
    }
}
