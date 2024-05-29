package Coordinate.coordikittyBE.domain.bookmark.dto;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
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

    public static BookmarkResponseDto fromEntity(PostEntity post, UserEntity user, HistoryEntity history) {
        return BookmarkResponseDto.builder()
                .postId(post.getPostId())
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
