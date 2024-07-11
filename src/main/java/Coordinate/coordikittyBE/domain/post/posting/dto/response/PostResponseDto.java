package Coordinate.coordikittyBE.domain.post.posting.dto.response;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.entity.PostImage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public record PostResponseDto(
        UUID postId,
        String content,
        Style style,
        int postLike,
        LocalDateTime uploadDate,
        String uploaderEmail,
        String uploaderNickname,
        String uploaderProfileImg,
        Boolean isLiked,
        Boolean isBookmarked,
        List<PostImage> postImgs
){
    public static PostResponseDto fromEntity(Post post, History history) {
        return new PostResponseDto(
                post.getId(),
                post.getContent(),
                post.getStyle(),
                post.getLikeCount(),
                post.getCreatedAt(),
                post.getUser().getEmail(),
                post.getUser().getNickname(),
                post.getUser().getProfileUrl(),
                history.getIsLiked(),
                history.getIsBookmarked(),
                post.getPostImgs()
        );
    }
}
