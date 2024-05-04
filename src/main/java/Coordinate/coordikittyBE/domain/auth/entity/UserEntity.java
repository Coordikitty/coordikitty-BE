package Coordinate.coordikittyBE.domain.auth.entity;

import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.follow.enity.FollowEntity;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="user")
public class UserEntity {
    @Id
    @Email
    @Column(name="user_id", nullable = false)
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

    @OneToMany(mappedBy = "userEntity")
    private List<ClothEntity> clothEntities;

//    @OneToMany(mappedBy = "userEntity")
//    private List<AlarmEntity> alarmEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<BookmarkEntity> bookmarkEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<HistoryEntity> historyRDBEntities;

    @OneToMany(mappedBy = "fromUser")
    private List<FollowEntity> followerEntities;

    @OneToMany(mappedBy = "toUser")
    private List<FollowEntity> followingEntities;
}


