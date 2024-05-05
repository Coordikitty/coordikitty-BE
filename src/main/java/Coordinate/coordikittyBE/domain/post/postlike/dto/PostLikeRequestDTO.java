package Coordinate.coordikittyBE.domain.post.postlike.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeRequestDTO {
    private UUID postId;
}
