package Coordinate.coordikittyBE.domain.post.entity;

import Coordinate.coordikittyBE.domain.attach.AttachEntity;
import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "post")
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class PostEntity {
    @Id
    @Column(name="id", nullable = false)
    private UUID postId;

    @Column(name="like_Count", nullable = false)
    private int likeCount;

    @Column(columnDefinition = "TEXT", name="content")
    private String content;

    @Column(name="season", nullable = true)
    private Season season;

    @Column(name = "situation", nullable = true)
    private Situation situation;

    @Column(name = "style", nullable = true)
    private Style style;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate modifiedAt;

    @OneToMany(mappedBy = "postEntity")
    private List<BookmarkEntity> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "postEntity")
    private List<AttachEntity> attaches = new ArrayList<>();

    @OneToMany(mappedBy = "postEntity")
    private List<HistoryEntity> historys = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public PostEntity update(PostUpdateRequestDto postUpdateRequestDto) {
        return PostEntity.builder()
                .postId(this.postId)
                .likeCount(this.likeCount)
                .content(postUpdateRequestDto.getContent())
                .style(postUpdateRequestDto.getStyle())
                .createdAt(this.createdAt)
                .modifiedAt(LocalDate.now())
                .bookmarks(this.bookmarks)
                .attaches(this.attaches)
                .historys(this.historys)
                .build();
    }
}
