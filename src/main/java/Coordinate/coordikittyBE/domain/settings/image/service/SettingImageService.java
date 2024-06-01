package Coordinate.coordikittyBE.domain.settings.image.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;

import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.image.dto.SettingImageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingImageService {

    private final UserRepository userRepository;

    public SettingImageResponseDto getSettingImage(String email) {
        // user id 로 profile_url 찾기
        Optional<User> userEntityOptional = userRepository.findById(email);
        SettingImageResponseDto settingImageResponseDTO = new SettingImageResponseDto();

        if (userEntityOptional.isEmpty()) return settingImageResponseDTO;
        User user = userEntityOptional.get();

        settingImageResponseDTO.setProfileURL(user.getProfileUrl());

        return settingImageResponseDTO;
    }

    public boolean setSettingImage(String email, MultipartFile profileImg) {
        // user id 로 찾아서 profile Img 변경
        Optional<User> userEntityOptional = userRepository.findById(email);

        if (userEntityOptional.isEmpty()) return false;
        User user = userEntityOptional.get();

        // profile Img 를 firebase에 저장
        String profileUrl = "www.firebase.com/profileUrl";
        user.setProfileUrl(profileUrl);

        userRepository.save(user);

        return true;
    }
}
