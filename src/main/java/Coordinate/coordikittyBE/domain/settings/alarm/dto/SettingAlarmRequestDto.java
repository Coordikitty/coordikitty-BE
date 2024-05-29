package Coordinate.coordikittyBE.domain.settings.alarm.dto;

import Coordinate.coordikittyBE.domain.settings.alarm.enums.AlarmType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettingAlarmRequestDto {
    private AlarmType type;
}
