package Coordinate.coordikittyBE.domain.post.posting.dto.response;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostlistResponseDto {
    private UUID postId;
    private String content;
    private Style style;
    private int postLike;
    private LocalDateTime uploadDate;
    private String uploaderEmail;
    private String uploaderNickname;
    private String uploaderProfileImg;
    private Boolean isLiked;
    private Boolean isBookmarked;
    private List<String> postImgs;

    public static PostlistResponseDto of(Post post, History history) {
        return PostlistResponseDto.builder()
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
