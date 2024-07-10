package Coordinate.coordikittyBE.domain.settings.profile.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDto;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileResponseDto;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingProfileService {

    private final UserRepository userRepository;

    public SettingProfileResponseDto getSettingProfile(String email) {
        // user id 로 회원정보 조회
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        return SettingProfileResponseDto.fromEntity(user);
    }

    public void setSettingProfile(String email, SettingProfileRequestDto nickname) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        //user.setNickname(nickname.getNickname());
        user = user.update(nickname.nickname());
        userRepository.save(user);
    }
}
