package Coordinate.coordikittyBE.domain.post.posting.dto.response;


import Coordinate.coordikittyBE.domain.attach.entity.Attach;

import java.util.List;

public record PostUpdateResponseDto(List<Attach> attaches) {
    public static PostUpdateResponseDto to(List<Attach> attaches) {
        return new PostUpdateResponseDto(attaches);
    }
}
