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
        Optional<User> userEntityOptional = userRepository.findById(email);
        SettingProfileResponseDto settingProfileResponseDTO = new SettingProfileResponseDto();

        if (userEntityOptional.isEmpty()) return settingProfileResponseDTO;
        User user = userEntityOptional.get();

        settingProfileResponseDTO.setName(user.getName());
        settingProfileResponseDTO.setNickname(user.getNickname());
        settingProfileResponseDTO.setNumber(user.getPhoneNumber());

        return settingProfileResponseDTO;
    }

    public boolean setSettingProfile(String email, SettingProfileRequestDto nickname) {
        Optional<User> userEntityOptional = userRepository.findById(email);

        if (userEntityOptional.isEmpty()) return false;
        User user = userEntityOptional.get();

        user.setNickname(nickname.getNickname());

        userRepository.save(user);

        return true;
    }
}
