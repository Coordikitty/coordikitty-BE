package Coordinate.coordikittyBE.domain.post.posting.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private UUID postId;
    private Season season;
    private Situation situation;
    private Style style;
    private int postLike;
    private LocalDate uploadDate;
    private String uploaderEmail;
    private String uploaderNickname;
    private String uploaderProfileImg;
    private Boolean isLiked;
    private Boolean isBookmarked;

    public static PostResponseDto fromEntity(Post post, User user, History history) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .season(post.getSeason())
                .situation(post.getSituation())
                .style(post.getStyle())
                .postLike(post.getLikeCount())
                .uploadDate(post.getCreatedAt())
                .uploaderEmail(user.getEmail())
                .uploaderNickname(user.getNickname())
                .uploaderProfileImg(user.getProfileUrl())
                .isLiked(history.getIsLiked())
                .isBookmarked(history.getIsBookmarked())
                .build();
    }
}
