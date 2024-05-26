package Coordinate.coordikittyBE.domain.settings.image.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.image.dto.SettingImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingImageService {

    private final UserRepository authRepository;

    public SettingImageResponseDTO getSettingImage(String email) {
        // user id 로 profile_url 찾기
        Optional<UserEntity> userEntityOptional = authRepository.findById(email);
        SettingImageResponseDTO settingImageResponseDTO = new SettingImageResponseDTO();

        if (userEntityOptional.isEmpty()) return settingImageResponseDTO;
        UserEntity userEntity = userEntityOptional.get();

        settingImageResponseDTO.setProfileURL(userEntity.getProfileUrl());

        return settingImageResponseDTO;
    }

    public boolean setSettingImage(String email, MultipartFile profileImg) {
        // user id 로 찾아서 profile Img 변경
        Optional<UserEntity> userEntityOptional = authRepository.findById(email);

        if (userEntityOptional.isEmpty()) return false;
        UserEntity userEntity = userEntityOptional.get();

        // profile Img 를 firebase에 저장
        String profileUrl = "www.firebase.com/profileUrl";
        userEntity.setProfileUrl(profileUrl);

        authRepository.save(userEntity);

        return true;
    }
}
