package Coordinate.coordikittyBE.domain.post.posting.service;


import Coordinate.coordikittyBE.domain.attach.Attach;
import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.bookmark.entity.Bookmark;
import Coordinate.coordikittyBE.domain.bookmark.repository.BookmarkRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.history.History;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.posting.dto.*;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final PostListBuilder postListBuilder;
    private final ClothRepository clothRepository;
    private final AttachRepository attachRepository;
    private final BookmarkRepository bookmarkRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public List<PostlistResponseDto> getPosts(UserDetails userDetails) {
        // 페이지 번호에 맞는 게시글 반환
        // 반환 게시글 리스트 = 최신 + 인기 + 추천

        List<PostlistResponseDto> posts = new ArrayList<>();
        List<Post> postEntities = postRepository.findAll();
        String email = userDetails.getUsername();

        // 최신
        Comparator<Post> comparatorNew = Comparator.comparing(Post::getCreatedAt).reversed();
        postListBuilder.listBuilder(comparatorNew, posts, postEntities, email);

        // 인기
        Comparator<Post> comparatorPopular = Comparator.comparing(Post::getLikeCount).reversed();
        postListBuilder.listBuilder(comparatorPopular, posts, postEntities, email);

        // 추천

        return posts;
    }

    public PostResponseDto findById(UUID postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return postConverter.toDto(post.get());
        }
        else{
            throw new RuntimeException("게시글 없음");
        }
    }

    public void delete(UUID postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void upload(PostUploadRequestDto postUploadRequestDto, String email) {
        User user = userRepository.findById(email).orElse(null);
        Post post = postConverter.fromDto(postUploadRequestDto, user);
        postRepository.save(post);

        Bookmark bookmark = Bookmark.of(user, post);
        post.getBookmarks().add(bookmark);
        bookmarkRepository.save(bookmark);

        History history = History.of(user, post);
        historyRepository.save(history);
        post.getHistorys().add(history);
        post.getAttaches().addAll(createAttaches(postUploadRequestDto.getClothIds(), post));
        postRepository.save(post);

    }

    @Transactional
    public PostUpdateResponseDto update(UUID postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("해당 게시글 없음"));

        attachRepository.deleteByPostId(postId);

        List<Attach> attaches = createAttaches(postUpdateRequestDto.getClothIds(), post);
        post.update(postUpdateRequestDto, attaches);
        return PostUpdateResponseDto.to(attaches);

    }

    private List<Attach> createAttaches(List<UUID> clothIds, Post post) {
        List<Attach> attaches = new ArrayList<>();
        for (UUID clothId : clothIds) {
            Cloth cloth = clothRepository.findById(clothId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 옷 없음."));
            Attach attach = Attach.of(cloth, post);
            attachRepository.save(attach);
            attaches.add(attach);
        }
        return attaches;
    }
}
