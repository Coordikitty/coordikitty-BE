package Coordinate.coordikittyBE.domain.post.entity;

import Coordinate.coordikittyBE.domain.attach.AttachEntity;
import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.closet.enums.Season;
import Coordinate.coordikittyBE.domain.post.enums.Situation;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.HistoryRDBEntity;
import Coordinate.coordikittyBE.domain.page.alarm.entity.AlarmEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Timestamp;
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
    @Column(name="post_id", nullable = false)
    private UUID postId;

    @Column(name="like_Count", nullable = false)
    private int likeCount;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "season", nullable = true)
    private Season season;

    @Column(name = "situation", nullable = true)
    private Situation situation;

    @Column(name = "style", nullable = true)
    private Style style;

    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp modifiedAt;

    @OneToMany(mappedBy = "postEntity")
    private List<BookmarkEntity> bookmarkEntities;

    @OneToMany(mappedBy = "postEntity")
    private List<AttachEntity> attachEntities;

    @OneToMany(mappedBy = "postEntity")
    private List<HistoryRDBEntity> historyRDBEntities;
}
