package Coordinate.coordikittyBE.domain.settings.image.dto;

import org.springframework.web.multipart.MultipartFile;

public record SettingImageRequestDto(MultipartFile image) {
}
