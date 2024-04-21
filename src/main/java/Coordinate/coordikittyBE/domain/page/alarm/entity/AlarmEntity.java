package Coordinate.coordikittyBE.domain.page.alarm.entity;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "alarm")
public class AlarmEntity {
    @Id
    private UUID alarmId;

    private String type;

    private Boolean actived;

    @TimeToLive
    private long ttl;

    @ManyToOne  // Many = Alarm, One = User
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private PostEntity postEntity;
}
