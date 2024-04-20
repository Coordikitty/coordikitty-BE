package Coordinate.coordikittyBE.page.alarm.entity;

import Coordinate.coordikittyBE.auth.entity.UserEntity;
import Coordinate.coordikittyBE.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "alarm")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmEntity {
    @Id
    @Column(name="alarm_id", nullable = false)
    private UUID alarmId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "actived", nullable = false)
    private Boolean actived;

    @ManyToOne  // Many = Alarm, One = User
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
}
