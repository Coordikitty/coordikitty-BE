package Coordinate.coordikittyBE.domain.settings.image.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SettingImageRequestDTO {
    private MultipartFile image;
}
