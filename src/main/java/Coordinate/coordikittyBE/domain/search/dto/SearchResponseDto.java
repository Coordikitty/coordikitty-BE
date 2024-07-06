package Coordinate.coordikittyBE.domain.search.dto;

import java.time.LocalDateTime;


public record SearchResponseDto (
    int postId,
    int postLike,
    LocalDateTime uploadDate,
    String uploaderEmail,
    String uploaderNickname,
    String uploaderProfileImg,
    String thumbnail,
    Boolean isLiked,
    Boolean isBookmarked
) {
}