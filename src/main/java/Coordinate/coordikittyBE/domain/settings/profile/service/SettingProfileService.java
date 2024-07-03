package Coordinate.coordikittyBE.domain.settings.profile.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDto;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingProfileService {

    private final UserRepository userRepository;

    public SettingProfileResponseDto getSettingProfile(String email) {
        // user id 로 회원정보 조회
        User user = userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email: " + email));

        return SettingProfileResponseDto.from(user);
    }

    public void setSettingProfile(String email, SettingProfileRequestDto nickname) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email: " + email));

        //user.setNickname(nickname.getNickname());
        userRepository.save(user);
    }
}
