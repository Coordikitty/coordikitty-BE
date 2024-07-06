package Coordinate.coordikittyBE.domain.auth.signup.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;

public record SignUpResponseDto(String email, String name) {
    public static SignUpResponseDto fromEntity(User user){
        return new SignUpResponseDto(user.getEmail(), user.getName());
    }
}
