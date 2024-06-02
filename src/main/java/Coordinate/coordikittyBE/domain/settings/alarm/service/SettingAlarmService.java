package Coordinate.coordikittyBE.domain.settings.alarm.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmRequestDto;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingAlarmService {

    private final UserRepository userRepository;

    public SettingAlarmResponseDto getSettingAlarm(String email) {
        // user id 로 현재 유저의 알람 설정 상태 반환
        User user = userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email: " + email));
        SettingAlarmResponseDto settingAlarmResponseDTO = new SettingAlarmResponseDto();

        if (user.getAlarm_feed() != null)
            settingAlarmResponseDTO.setAlarm_feed(user.getAlarm_feed());
        else {
            user.setAlarm_feed(false);
            settingAlarmResponseDTO.setAlarm_feed(false);
        }

        if (user.getAlarm_follow() != null)
            settingAlarmResponseDTO.setAlarm_follow(user.getAlarm_follow());
        else {
            user.setAlarm_follow(false);
            settingAlarmResponseDTO.setAlarm_follow(false);
        }

        if (user.getAlarm_like() != null)
            settingAlarmResponseDTO.setAlarm_like(user.getAlarm_like());
        else {
            user.setAlarm_like(false);
            settingAlarmResponseDTO.setAlarm_like(false);
        }

        userRepository.save(user);

        return settingAlarmResponseDTO;
    }

    public void setSettingAlarm(String email, SettingAlarmRequestDto type) {
        // user id 로 타입에 맞는 유저의 알람 설정 변경
        User user = userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email: " + email));

        boolean newValue;
        switch (type.getType()) {
            case FEED -> {
                newValue = user.getAlarm_feed() != null && !user.getAlarm_feed();
                user.setAlarm_feed(newValue);
            }
            case FOLLOW -> {
                newValue = user.getAlarm_follow() != null && !user.getAlarm_follow();
                user.setAlarm_follow(newValue);
            }
            case LIKE -> {
                newValue = user.getAlarm_like() != null && !user.getAlarm_like();
                user.setAlarm_like(newValue);
            }
            default -> throw new IllegalStateException("Unexpected type value: " + type.getType());
        }

        userRepository.save(user);
    }
}
