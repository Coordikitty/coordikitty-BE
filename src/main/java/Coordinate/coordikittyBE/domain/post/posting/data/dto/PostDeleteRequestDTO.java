package Coordinate.coordikittyBE.domain.post.posting.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostDeleteRequestDTO {
    private UUID postId;
}
