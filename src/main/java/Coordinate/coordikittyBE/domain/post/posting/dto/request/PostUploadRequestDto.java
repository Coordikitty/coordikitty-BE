package Coordinate.coordikittyBE.domain.post.posting.dto.request;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostUploadRequestDto {
    private String content;
    private Style style;
    private List<String> postImgs;
    private List<UUID> clothIds;
}
