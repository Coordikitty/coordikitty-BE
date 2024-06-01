package Coordinate.coordikittyBE.domain.page.alarm.entity;

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
public class Alarm {
    @Id
    private UUID id;

    private String type;

    private Boolean actived;

    @TimeToLive
    private long ttl;

//    @ManyToOne  // Many = Alarm, One = User
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity userEntity;

//    @ManyToOne
//    @JoinColumn(name="post_id", nullable = false)
//    private PostEntity postEntity;
}
