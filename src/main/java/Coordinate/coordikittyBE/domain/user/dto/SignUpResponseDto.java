package Coordinate.coordikittyBE.domain.user.dto;

import Coordinate.coordikittyBE.domain.user.entity.User;

public record SignUpResponseDto(String email, String name) {
    public static SignUpResponseDto fromEntity(User user){
        return new SignUpResponseDto(user.getEmail(), user.getName());
    }
}
