package Coordinate.coordikittyBE.domain.settings.image.dto;


import Coordinate.coordikittyBE.domain.user.entity.User;

public record SettingImageResponseDto(String profileURL) {
    public static SettingImageResponseDto fromEntity(User user) {
        return new SettingImageResponseDto(user.getProfileUrl());
    }
}
