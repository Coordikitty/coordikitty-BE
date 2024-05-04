package Coordinate.coordikittyBE.domain.post.postbookmark.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostBookmarkRequestDTO {
    private UUID postId;
}
