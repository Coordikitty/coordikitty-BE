package Coordinate.coordikittyBE.domain.settings.alarm.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;

public record SettingAlarmResponseDto(boolean alarm_like, boolean alarm_feed, boolean alarm_follow) {
    public static SettingAlarmResponseDto from(User user) {
        return new SettingAlarmResponseDto(user.getAlarm_like(), user.getAlarm_feed(), user.getAlarm_follow());
    }
}
