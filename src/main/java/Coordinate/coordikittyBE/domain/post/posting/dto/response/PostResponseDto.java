package Coordinate.coordikittyBE.domain.post.posting.dto.response;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private UUID postId;
    private String content;
    private Style style;
    private int postLike;
    private List<String> postImgs;
    private LocalDateTime uploadDate;
    private String uploaderEmail;
    private String uploaderNickname;
    private String uploaderProfileImg;
    private Boolean isLiked;
    private Boolean isBookmarked;

    public static PostResponseDto of(Post post, History history) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .content(post.getContent())
                .style(post.getStyle())
                .postLike(post.getLikeCount())
                .postImgs(post.getPostImgs())
                .uploadDate(post.getCreatedAt())
                .uploaderEmail(post.getUser().getEmail())
                .uploaderNickname(post.getUser().getNickname())
                .uploaderProfileImg(post.getUser().getProfileUrl())
                .isLiked(history.getIsLiked())
                .isBookmarked(history.getIsBookmarked())
                .build();
    }
}
