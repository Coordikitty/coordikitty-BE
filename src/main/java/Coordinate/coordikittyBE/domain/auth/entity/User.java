package Coordinate.coordikittyBE.domain.auth.entity;


import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.follow.enity.Follow;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.post.entity.Post;
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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name="user")
public class User implements UserDetails {
    @Id
    @Email
    @Column(name="email")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Cloth> clothes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<History> historys = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.REMOVE)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.REMOVE)
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "tags_id")
    private Tags tags;

    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }

    public void setting(boolean alarm_like, boolean alarm_feed, boolean alarm_follow){
        this.alarm_like = alarm_like;
        this.alarm_feed = alarm_feed;
        this.alarm_follow = alarm_follow;
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


