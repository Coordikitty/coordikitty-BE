package Coordinate.coordikittyBE.domain.settings.image.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SettingImageRequestDto {
    private MultipartFile image;
}
