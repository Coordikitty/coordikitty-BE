package Coordinate.coordikittyBE.domain.settings.alarm.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.AuthRepository;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmRequestDTO;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingAlarmService {

    private final AuthRepository authRepository;

    public SettingAlarmResponseDTO getSettingAlarm(String email) {
        // user id 로 현재 유저의 알람 설정 상태 반환
        Optional<UserEntity> userEntityOptional = authRepository.findById(email);
        SettingAlarmResponseDTO settingAlarmResponseDTO = new SettingAlarmResponseDTO();

        if(userEntityOptional.isEmpty()) return settingAlarmResponseDTO;
        UserEntity userEntity = userEntityOptional.get();

        settingAlarmResponseDTO.setAlarm_feed(userEntity.isAlarm_feed());
        settingAlarmResponseDTO.setAlarm_follow(userEntity.isAlarm_follow());
        settingAlarmResponseDTO.setAlarm_like(userEntity.isAlarm_like());

        return settingAlarmResponseDTO;
    }

    public boolean setSettingAlarm(String email, SettingAlarmRequestDTO type) {
        // user id 로 타입에 맞는 유저의 알람 설정 변경
        Optional<UserEntity> userEntityOptional = authRepository.findById(email);

        if(userEntityOptional.isEmpty()) return false;
        UserEntity userEntity = userEntityOptional.get();

//        switch (type.getType()) {
//            case FEED -> userEntity.setAlarm_feed(!userEntity.isAlarm_feed());
//            case FOLLOW -> userEntity.setAlarm_follow(!userEntity.isAlarm_follow());
//            case LIKE -> userEntity.setAlarm_like(!userEntity.isAlarm_like());
//            default -> throw new IllegalStateException("Unexpected value: " + type.getType());
//        }

        authRepository.save(userEntity);

        return true;
    }
}
