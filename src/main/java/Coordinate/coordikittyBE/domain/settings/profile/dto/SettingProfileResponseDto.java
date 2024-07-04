package Coordinate.coordikittyBE.domain.settings.profile.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;

public record SettingProfileResponseDto(String name, String phoneNumber, String nickname) {
    public static SettingProfileResponseDto from(User user) {
        return new SettingProfileResponseDto(user.getName(), user.getPhoneNumber(), user.getNickname());
    }
}
