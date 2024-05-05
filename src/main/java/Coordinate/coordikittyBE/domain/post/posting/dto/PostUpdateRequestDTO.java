package Coordinate.coordikittyBE.domain.post.posting.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.channels.MulticastChannel;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequestDTO {
    private UUID postId;
    private String content;
    private Situation situation;
    private Style style;
    private List<MulticastChannel> postImgs;
}
