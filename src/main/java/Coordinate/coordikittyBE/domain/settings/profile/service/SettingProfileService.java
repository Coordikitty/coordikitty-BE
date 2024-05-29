package Coordinate.coordikittyBE.domain.settings.profile.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
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
        Optional<UserEntity> userEntityOptional = userRepository.findById(email);
        SettingProfileResponseDto settingProfileResponseDTO = new SettingProfileResponseDto();

        if (userEntityOptional.isEmpty()) return settingProfileResponseDTO;
        UserEntity userEntity = userEntityOptional.get();

        settingProfileResponseDTO.setName(userEntity.getName());
        settingProfileResponseDTO.setNickname(userEntity.getNickname());
        settingProfileResponseDTO.setNumber(userEntity.getPhoneNumber());

        return settingProfileResponseDTO;
    }

    public boolean setSettingProfile(String email, SettingProfileRequestDto nickname) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(email);

        if (userEntityOptional.isEmpty()) return false;
        UserEntity userEntity = userEntityOptional.get();

        userEntity.setNickname(nickname.getNickname());

        userRepository.save(userEntity);

        return true;
    }
}
