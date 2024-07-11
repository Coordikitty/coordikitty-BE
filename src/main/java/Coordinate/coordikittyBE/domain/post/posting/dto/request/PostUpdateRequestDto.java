package Coordinate.coordikittyBE.domain.post.posting.dto.request;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

public record PostUpdateRequestDto(
        String content,
        Situation situation,
        Style style,
        List<MultipartFile> postImgs,
        List<UUID> clothIds
) {
}
