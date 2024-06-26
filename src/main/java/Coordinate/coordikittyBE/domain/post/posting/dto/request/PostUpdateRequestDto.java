package Coordinate.coordikittyBE.domain.post.posting.dto.request;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String content;
    private Situation situation;
    private Style style;
    private List<MultipartFile> postImgs;
    private List<UUID> clothIds;
}
