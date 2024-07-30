package Coordinate.coordikittyBE.domain.user.dto;

import Coordinate.coordikittyBE.domain.user.entity.User;
import java.util.UUID;

public record SignUpSocialRequestDto(String email) {

    public static User toEntity(SignUpSocialRequestDto signUpSocialRequestDto) {
        return User.builder()
                .email(signUpSocialRequestDto.email())
                .password(UUID.randomUUID().toString())
                .build();
    }
}
