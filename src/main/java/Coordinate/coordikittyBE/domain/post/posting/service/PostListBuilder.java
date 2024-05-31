package Coordinate.coordikittyBE.domain.post.posting.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostlistResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostListBuilder {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    public void listBuilder(
            Comparator<PostEntity> comparator,
            List<PostlistResponseDto> postResponses,
            List<PostEntity> posts,
            String email
    ) {
        posts.sort(comparator);

        for (PostEntity postEntity : posts) {
            List<HistoryEntity> historys = historyRepository.findAllByPostIdandUserId(postEntity.getPostId(), email);
            UserEntity user = postEntity.getUserEntity();
            if (!historys.isEmpty()) {
                PostlistResponseDto postlistResponseDto = PostlistResponseDto.builder()
                                            .postId(postEntity.getPostId())
                                            .season(postEntity.getSeason())
                                            .situation(postEntity.getSituation())
                                            .style(postEntity.getStyle())
                                            .postLike(postEntity.getLikeCount())
                                            .uploadDate(postEntity.getCreatedAt())
                                            .uploaderEmail(user.getEmail())
                                            .uploaderNickname(user.getNickname())
                                            .uploaderProfileImg(user.getProfileUrl())
                                            .thumbnail("thumbnail")
                                            .isLiked(historys.getFirst().getIsLiked())
                                            .isBookmarked(historys.getFirst().getIsBookmarked())
                                            .build();
                postResponses.add(postlistResponseDto);
            }
        }
        System.out.println(postResponses.isEmpty());
    }
}
