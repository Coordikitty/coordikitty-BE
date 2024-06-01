package Coordinate.coordikittyBE.domain.post.posting.dto;


import Coordinate.coordikittyBE.domain.attach.Attach;
import Coordinate.coordikittyBE.domain.post.entity.Post;

import java.util.List;
import java.util.UUID;

public record PostUpdateResponseDto(List<Attach> attaches) {
    public static PostUpdateResponseDto to(List<Attach> attaches) {
        return new PostUpdateResponseDto(attaches);
    }
}
