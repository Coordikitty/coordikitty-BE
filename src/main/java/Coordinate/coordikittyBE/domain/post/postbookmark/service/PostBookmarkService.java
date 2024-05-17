package Coordinate.coordikittyBE.domain.post.postbookmark.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.bookmark.repository.BookmarkRepository;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<PostEntity> findPost = postRepository.findById(postId);
        if (findPost.isPresent()) {
            PostEntity post = findPost.get();
            bookmarkRepository.save(BookmarkEntity.builder()
                    .bookmarkId(UUID.randomUUID())
                    .postEntity(post)
                    .userEntity(new UserEntity())//수정 필요
                    .build());
            return ResponseEntity.ok("북마크 추가 성공");
        }
        else{
            throw new RuntimeException("잘못된 북마크 요청");
        }

    }

    public ResponseEntity<String> deleteBookmark(UUID postId) {
        Optional<BookmarkEntity> findBookmark = bookmarkRepository.findById(postId);
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
