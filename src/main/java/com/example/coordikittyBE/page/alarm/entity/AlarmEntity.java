package com.example.coordikittyBE.page.alarm.entity;

import com.example.coordikittyBE.auth.entity.UserEntity;
import com.example.coordikittyBE.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "alarm")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AlarmEntity {
    @Id
    @Column(name="id", nullable = false)
    private UUID alarmId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "time", nullable = false)
    private String time;

    @ManyToOne  // Many = Alarm, One = User
    @JoinColumn(name = "email", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName = "id", nullable = false)
    private PostEntity postEntity;
}
