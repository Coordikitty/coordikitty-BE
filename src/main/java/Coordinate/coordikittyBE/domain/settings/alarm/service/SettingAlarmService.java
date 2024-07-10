package Coordinate.coordikittyBE.domain.settings.alarm.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmRequestDto;
import Coordinate.coordikittyBE.domain.settings.alarm.dto.SettingAlarmResponseDto;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
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
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        boolean alarm_like = getAlarmLike(user);
        boolean alarm_feed = getAlarmFeed(user);
        boolean alarm_follow = getAlarmFollow(user);

        user.setting(alarm_like, alarm_feed, alarm_follow);
        userRepository.save(user);

        return SettingAlarmResponseDto.from(user);
    }

    public void setSettingAlarm(String email, SettingAlarmRequestDto type) {
        // user id 로 타입에 맞는 유저의 알람 설정 변경
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        boolean alarm_like = getAlarmLike(user);
        boolean alarm_feed = getAlarmFeed(user);
        boolean alarm_follow = getAlarmFollow(user);

        switch (type.type()) {
            case LIKE -> user.setting(!alarm_like, alarm_feed, alarm_follow);
            case FEED -> user.setting(alarm_like, !alarm_feed, alarm_follow);
            case FOLLOW -> user.setting(alarm_like, alarm_feed, !alarm_follow);
            default -> throw new CoordikittyException(ErrorType.INVALID_REQUEST_ERROR);
        }

        userRepository.save(user);
    }

    private boolean getAlarmLike(User user) {
        return Optional.ofNullable(user.getAlarm_like()).orElse(false);
    }
    private boolean getAlarmFeed(User user) {
        return Optional.ofNullable(user.getAlarm_feed()).orElse(false);
    }
    private boolean getAlarmFollow(User user) {
        return Optional.ofNullable(user.getAlarm_follow()).orElse(false);
    }
}
