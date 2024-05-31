package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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
                .isLiked(true)
                .isBookmarked(true)
                .build();
    }

    public PostEntity fromDto(PostUploadRequestDto postUploadRequestDto){
        return PostEntity.builder()
                .postId(UUID.randomUUID())
                .likeCount(0)
                .content(postUploadRequestDto.getContent())
                .style(postUploadRequestDto.getStyle())
                .bookmarks(new ArrayList<>())
                .attaches(new ArrayList<>())
                .historys(new ArrayList<>())
                .createdAt(LocalDate.now())
                .modifiedAt(LocalDate.now())
                .build();
    }
}
