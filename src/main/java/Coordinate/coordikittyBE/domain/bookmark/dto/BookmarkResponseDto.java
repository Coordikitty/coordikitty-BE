package Coordinate.coordikittyBE.domain.bookmark.dto;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class BookmarkResponseDto {
    private UUID postId;
    private Season season;
    private Situation situation;
    private Style style;
    private int postLike;
    private LocalDate uploadDate;
    private String uploaderEmail;
    private String uploaderProfileImg;
    private String thumbnail;
    private Boolean isLiked;

    public static BookmarkResponseDto fromEntity(Post post, User user, History history) {
        return BookmarkResponseDto.builder()
                .postId(post.getId())
                .season(post.getSeason())
                .situation(post.getSituation())
                .style(post.getStyle())
                .postLike(post.getLikeCount())
                .uploadDate(post.getCreatedAt())
                .uploaderEmail(user.getEmail())
                .uploaderProfileImg(user.getProfileUrl())
                .isLiked(history.getIsLiked())
                .build();
    }
}
