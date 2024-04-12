package Coordinate.coordikittyBE.post.entity;

import Coordinate.coordikittyBE.page.alarm.entity.AlarmEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {
    @Id
    @Column(name="id", nullable = false)
    private UUID postId;
    @Column(name="likeCount", nullable = false)
    private int likeCount;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy="postEntity")
    private List<AlarmEntity> alarmEntities;


}
