package Coordinate.coordikittyBE.domain.settings.image.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;

import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.settings.image.dto.SettingImageResponseDto;
import Coordinate.coordikittyBE.domain.settings.image.repository.ImageDao;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingImageService {

    private final UserRepository userRepository;
    private final ImageDao imageDao;

    public SettingImageResponseDto getSettingImage(String email) {
        // user id 로 profile_url 찾기
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        return SettingImageResponseDto.fromEntity(user);
    }

    public void setSettingImage(String email, MultipartFile profileImg) {
        // user id 로 찾아서 profile Img 변경
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        // 기존 이미지가 있는 경우, 기존 이미지 삭제
        if (user.getProfileUrl() != null) imageDao.delete(email);

        // profile Img 를 firebase에 저장
        String profileUrl = imageDao.upload(profileImg, email);
        user.addProfileUrl(profileUrl);
        userRepository.save(user);
    }

    public void deleteSettingImage(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));

        // 기존 이미지가 있는 경우, 기존 이미지 삭제
        if (user.getProfileUrl() != null) imageDao.delete(email);

        // profile url 을 null 로 저장
        user.addProfileUrl(null);
        userRepository.save(user);
    }
}
