package Coordinate.coordikittyBE.domain.settings.alarm.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
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
        Optional<UserEntity> userEntityOptional = userRepository.findById(email);
        SettingAlarmResponseDto settingAlarmResponseDTO = new SettingAlarmResponseDto();

        if(userEntityOptional.isEmpty()) return settingAlarmResponseDTO;
        UserEntity userEntity = userEntityOptional.get();

        if (userEntity.getAlarm_feed() != null)
            settingAlarmResponseDTO.setAlarm_feed(userEntity.getAlarm_feed());
        else {
            userEntity.setAlarm_feed(false);
            settingAlarmResponseDTO.setAlarm_feed(false);
        }

        if (userEntity.getAlarm_follow() != null)
            settingAlarmResponseDTO.setAlarm_follow(userEntity.getAlarm_follow());
        else {
            userEntity.setAlarm_follow(false);
            settingAlarmResponseDTO.setAlarm_follow(false);
        }

        if (userEntity.getAlarm_like() != null)
            settingAlarmResponseDTO.setAlarm_like(userEntity.getAlarm_like());
        else {
            userEntity.setAlarm_like(false);
            settingAlarmResponseDTO.setAlarm_like(false);
        }

        userRepository.save(userEntity);

        return settingAlarmResponseDTO;
    }

    public boolean setSettingAlarm(String email, SettingAlarmRequestDto type) {
        // user id 로 타입에 맞는 유저의 알람 설정 변경
        Optional<UserEntity> userEntityOptional = userRepository.findById(email);

        if(userEntityOptional.isEmpty()) return false;
        UserEntity userEntity = userEntityOptional.get();

        boolean newValue;
        switch (type.getType()) {
            case FEED -> {
                newValue = userEntity.getAlarm_feed() != null && !userEntity.getAlarm_feed();
                userEntity.setAlarm_feed(newValue);
            }
            case FOLLOW -> {
                newValue = userEntity.getAlarm_follow() != null && !userEntity.getAlarm_follow();
                userEntity.setAlarm_follow(newValue);
            }
            case LIKE -> {
                newValue = userEntity.getAlarm_like() != null && !userEntity.getAlarm_like();
                userEntity.setAlarm_like(newValue);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type.getType());
        }

        userRepository.save(userEntity);

        return true;
    }
}
