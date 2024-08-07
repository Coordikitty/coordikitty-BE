package Coordinate.coordikittyBE.domain.settings.profile.dto;

import Coordinate.coordikittyBE.domain.user.entity.User;

public record SettingProfileResponseDto(String name, String phoneNumber, String nickname) {
    public static SettingProfileResponseDto fromEntity(User user) {
        return new SettingProfileResponseDto(user.getName(), user.getPhoneNumber(), user.getNickname());
    }
}
