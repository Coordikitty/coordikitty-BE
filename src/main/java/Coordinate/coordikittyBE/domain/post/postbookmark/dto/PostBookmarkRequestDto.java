package Coordinate.coordikittyBE.domain.post.postbookmark.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostBookmarkRequestDto {
    private UUID postId;
}
