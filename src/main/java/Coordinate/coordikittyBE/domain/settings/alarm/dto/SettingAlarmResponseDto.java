package Coordinate.coordikittyBE.domain.settings.alarm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettingAlarmResponseDto {
    private boolean alarm_like;
    private boolean alarm_feed;
    private boolean alarm_follow;
}
