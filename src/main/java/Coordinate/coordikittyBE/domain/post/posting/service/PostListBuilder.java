package Coordinate.coordikittyBE.domain.post.posting.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.history.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
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
            Comparator<Post> comparator,
            List<PostlistResponseDto> postResponses,
            List<Post> posts,
            String email
    ) {
        posts.sort(comparator);

        for (Post post : posts) {
            List<History> historys = historyRepository.findAllByPostIdAndUserEmail(post.getId(), email);
            User user = post.getUser();
            if (!historys.isEmpty()) {
                PostlistResponseDto postlistResponseDto = PostlistResponseDto.builder()
                                            .postId(post.getId())
                                            .season(post.getSeason())
                                            .situation(post.getSituation())
                                            .style(post.getStyle())
                                            .postLike(post.getLikeCount())
                                            .uploadDate(post.getCreatedAt())
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
