package Coordinate.coordikittyBE.domain.auth.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity(name="user")
public class UserEntity implements UserDetails {
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

    @Column(name = "alarm_like", nullable = true)
    private boolean alarm_like;

    @Column(name = "alarm_feed", nullable = true)
    private boolean alarm_feed;

    @Column(name = "alarm_follow", nullable = true)
    private boolean alarm_follow;

    @Column(name = "cloth_id", nullable = true)
    private UUID clothId;

//    @OneToMany(mappedBy = "userEntity")
//    private List<AlarmEntity> alarmEntities;
    @Column(name = "bookmark_user_id", nullable = true)
    private UUID bookmarkId;

    @Column(name = "history_user_id", nullable = true)
    private UUID historyId;

    @Column(name = "follower_id", nullable = true)
    private UUID followerId;

    @Column(name = "following_id", nullable = true)
    private UUID followingId;

    @Column(name = "post_id", nullable = true)
    private UUID postId;

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


