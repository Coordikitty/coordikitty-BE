package Coordinate.coordikittyBE.domain.post.posting.service;

import Coordinate.coordikittyBE.domain.attach.AttachEntity;
import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    public PostResponseDto findById(UUID postId) {
        Optional<PostEntity> post = postRepository.findById(postId);
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

    public void upload(PostUploadRequestDto postUploadRequestDto) {
        var entity = PostEntity.builder()
                .postId(UUID.randomUUID())
                .likeCount(0)
                .content(postUploadRequestDto.getContent())
                .style(postUploadRequestDto.getStyle())
                .createdAt(LocalDate.now())
                .modifiedAt(null)
                .bookmarkEntities(null)
                .attachEntities(null)
                .historyRDBEntities(null)
                .build();
        postRepository.save(entity);

    }
}
