package Coordinate.coordikittyBE.domain.post.posting.dto;

import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
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

    public static PostlistResponseDto fromEntity(Post post, History history) {
        return PostlistResponseDto.builder()
                .postId(post.getId())
                .style(post.getStyle())
                .postLike(post.getLikeCount())
                .uploadDate(post.getCreatedAt())
                .uploaderEmail(post.getUser().getEmail())
                .uploaderNickname(post.getUser().getNickname())
                .uploaderProfileImg(post.getUser().getProfileUrl())
                .isLiked(history.getIsLiked())
                .isBookmarked(history.getIsBookmarked())
                .build();
    }
}
