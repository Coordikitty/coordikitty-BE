package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {
    public PostResponseDto toDto(PostEntity post){
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .season(post.getSeason())
                .situation(post.getSituation())
                .style(post.getStyle())
                .postLike(post.getLikeCount())
                .uploadDate(post.getCreatedAt())
                .uploaderEmail(post.getUserEntity().getEmail())
                .uploaderNickname(post.getUserEntity().getNickname())
                .uploaderProfileImg(post.getUserEntity().getProfileUrl())
                //.postImgs(post.getPostImgs())
                .cloth(post.getAttachEntities())
                .isLiked(true)
                .isBookmarked(true)
                .build();
    }
}
