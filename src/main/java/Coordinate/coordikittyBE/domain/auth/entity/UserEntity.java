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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="user")
public class UserEntity implements UserDetails {
    @Id
    @Email
    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="name", nullable = true)
    private String name;

    @Column(name="nickname", nullable = true, length = 10)
    private String nickname;

    @Column(name="birth", nullable = true)
    private LocalDate birth;

    @Column(name="phone_number", nullable = true)
    private String phoneNumber;

    @Column(name="tall", nullable = true)
    private int tall;

    @Column(name="shoesize", nullable = true)
    private int shoeSize;

    @Column(name="weight", nullable = true)
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

    public UserEntity update(String nickname){
        this.nickname = nickname;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
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


