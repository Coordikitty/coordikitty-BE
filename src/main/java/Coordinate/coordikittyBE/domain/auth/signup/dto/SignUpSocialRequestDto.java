package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public record SignUpSocialRequestDto(String email) {

    public static User from(SignUpSocialRequestDto signUpSocialRequestDto) {
        return User.builder()
                .email(signUpSocialRequestDto.email())
                .password(UUID.randomUUID().toString())
                .build();
    }

    public static SignUpSocialRequestDto fromEmail(String email) {
        return new SignUpSocialRequestDto(email);
    }
}
