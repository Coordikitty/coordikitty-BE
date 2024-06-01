package Coordinate.coordikittyBE.domain.post.postbookmark.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.bookmark.entity.Bookmark;
import Coordinate.coordikittyBE.domain.bookmark.repository.BookmarkRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostBookmarkService {
    private final PostRepository postRepository;
    private final BookmarkRepository bookmarkRepository;
    public ResponseEntity<String> addBookmark(UUID postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isPresent()) {
            Post post = findPost.get();
            bookmarkRepository.save(Bookmark.builder()
                    .id(UUID.randomUUID())
                    .post(post)
                    .user(new User())//수정 필요
                    .build());
            return ResponseEntity.ok("북마크 추가 성공");
        }
        else{
            throw new RuntimeException("잘못된 북마크 요청");
        }

    }

    public ResponseEntity<String> deleteBookmark(UUID postId) {
        Optional<Bookmark> findBookmark = bookmarkRepository.findById(postId);
        //북마크 검색할 방법 필요
        if (findBookmark.isPresent()) {
            bookmarkRepository.delete(findBookmark.get());
            return ResponseEntity.ok("북마크 제거 성공");
        }
        else{
            throw new RuntimeException("잘못된 북마크 제거 요청");
        }
    }
}
