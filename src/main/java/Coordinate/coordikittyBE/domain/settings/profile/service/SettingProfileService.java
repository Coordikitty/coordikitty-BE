package Coordinate.coordikittyBE.domain.settings.profile.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileRequestDTO;
import Coordinate.coordikittyBE.domain.settings.profile.dto.SettingProfileResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingProfileService {

    private final UserRepository authRepository;

    public SettingProfileResponseDTO getSettingProfile(String email) {
        // user id 로 회원정보 조회
        Optional<UserEntity> userEntityOptional = authRepository.findById(email);
        SettingProfileResponseDTO settingProfileResponseDTO = new SettingProfileResponseDTO();

        if (userEntityOptional.isEmpty()) return settingProfileResponseDTO;
        UserEntity userEntity = userEntityOptional.get();

        settingProfileResponseDTO.setName(userEntity.getName());
        settingProfileResponseDTO.setNickname(userEntity.getNickname());
        settingProfileResponseDTO.setNumber(userEntity.getPhoneNumber());

        return settingProfileResponseDTO;
    }

    public boolean setSettingProfile(String email, SettingProfileRequestDTO nickname) {
        Optional<UserEntity> userEntityOptional = authRepository.findById(email);

        if (userEntityOptional.isEmpty()) return false;
        UserEntity userEntity = userEntityOptional.get();

        //userEntity.setNickname(nickname.getNickname());
        authRepository.save(userEntity);

        return true;
    }
}
