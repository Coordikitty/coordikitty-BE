package Coordinate.coordikittyBE.domain.auth.entity;


import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import Coordinate.coordikittyBE.domain.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.domain.follow.enity.FollowEntity;
import Coordinate.coordikittyBE.domain.history.HistoryEntity;
import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity(name="user")
public class UserEntity implements UserDetails {
    @Id
    @Email
    @Column(name="email", nullable = false)
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
    private Boolean alarm_like;

    @Column(name = "alarm_feed", nullable = true)
    private Boolean alarm_feed;

    @Column(name = "alarm_follow", nullable = true)
    private Boolean alarm_follow;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<ClothEntity> clothes = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<BookmarkEntity> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<HistoryEntity> historys = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
    private List<FollowEntity> followers = new ArrayList<>();

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
    private List<FollowEntity> followings = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();

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


