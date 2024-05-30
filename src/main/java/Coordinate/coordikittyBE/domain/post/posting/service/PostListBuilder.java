package Coordinate.coordikittyBE.domain.post.posting.service;

import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostlistResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostListBuilder {
    private final HistoryRepository historyRepository;

    public void listBuilder(
            Comparator<PostEntity> comparator,
            List<PostlistResponseDto> posts,
            List<PostEntity> postEntities,
            String email
    ) {
        postEntities.sort(comparator);

        for (PostEntity postEntity : postEntities) {
            PostlistResponseDto postlistResponseDto = new PostlistResponseDto();

            postlistResponseDto.setPostId(postEntity.getPostId());
            postlistResponseDto.setSeason(postEntity.getSeason());
            postlistResponseDto.setSituation(postEntity.getSituation());
            postlistResponseDto.setStyle(postEntity.getStyle());
            postlistResponseDto.setPostLike(postEntity.getLikeCount());
            postlistResponseDto.setUploadDate(postEntity.getCreatedAt());
            postlistResponseDto.setUploaderEmail(postEntity.getUserEntity().getEmail());
            postlistResponseDto.setUploaderNickname(postEntity.getUserEntity().getNickname());
            postlistResponseDto.setUploaderProfileImg(postEntity.getUserEntity().getProfileUrl());
            postlistResponseDto.setThumbnail("thumbnail");

            Optional<List<HistoryEntity>> historyEntities = historyRepository.findAllByPostIdandUserId(postEntity.getPostId(), email);
            if (historyEntities.isPresent()) {
                postlistResponseDto.setIsLiked(historyEntities.get().getFirst().getIsLiked());
                postlistResponseDto.setIsBookmarked(historyEntities.get().getFirst().getIsBookmarked());

                posts.add(postlistResponseDto);
            }
        }
    }
}
