package Coordinate.coordikittyBE.domain.auth.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="user")
public class UserEntity {
    @Id
    @Email
    @Column(name="id", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="age", nullable = false)
    private int age;

    @Column(name="nickname", nullable = false, length = 10)
    private String nickname;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="tall", nullable = false)
    private int tall;

    @Column(name="shoesize", nullable = false)
    private int shoeSize;

    @Column(name="weight", nullable = false)
    private int weight;

    @Column(name = "profile_url", nullable = true)
    private String profileUrl;

    @Column(name = "alarm_like", nullable = false)
    private boolean alarm_like;

    @Column(name = "alarm_feed", nullable = false)
    private boolean alarm_feed;

    @Column(name = "alarm_follow", nullable = false)
    private boolean alarm_follow;

    @Column(name = "cloth_id")
    private UUID clothId;

//    @OneToMany(mappedBy = "userEntity")
//    private List<AlarmEntity> alarmEntities;
    @Column(name = "bookmark_user_id")
    private UUID bookmarkId;

    @Column(name = "history_user_id")
    private UUID historyId;

    @Column(name = "follower_id")
    private UUID followerId;

    @Column(name = "following_id")
    private UUID followingId;

    @Column(name = "post_id")
    private UUID postId;
}


