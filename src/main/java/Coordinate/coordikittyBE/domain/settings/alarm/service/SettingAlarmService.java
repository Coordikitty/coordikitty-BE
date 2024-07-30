package Coordinate.coordikittyBE.domain.settings.alarm.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmRequestDto;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmResponseDto;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SettingAlarmService {

    private final UserRepository userRepository;

    @Transactional
    public SettingAlarmResponseDto getSettingAlarm(String email) {
        // user id 로 현재 유저의 알람 설정 상태 반환
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));
        return SettingAlarmResponseDto.from(user);
    }

    @Transactional
    public void changeSettingAlarm(String email, SettingAlarmRequestDto type) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        switch (type.type()) {
            case LIKE -> user.settingLike();
            case FEED -> user.settingFeed();
            case FOLLOW -> user.settingFollow();
            default -> throw new CoordikittyException(ErrorType.INVALID_REQUEST_ERROR);
        }
    }

}
