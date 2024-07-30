package Coordinate.coordikittyBE.domain.post.entity;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="like_count", nullable = false)
    private int likeCount;

    @Column(columnDefinition = "TEXT", name="content")
    private String content;

    @Column(name = "style", nullable = false)
    private Style style;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

//    @ElementCollection(fetch = FetchType.LAZY)
//    private List<String> postImgs = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PostImage> postImgs = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Attach> attaches = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<History> historys = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(PostUpdateRequestDto postUpdateRequestDto, List<Attach> attaches, List<PostImage> postImgs) {
        this.content = postUpdateRequestDto.content();
        this.style = postUpdateRequestDto.style();

        this.attaches.clear();
        this.attaches.addAll(attaches);

        this.postImgs.clear();
        this.postImgs.addAll(postImgs);
    }

    public void like(){
        this.likeCount++;
    }
    public void unlike(){
        this.likeCount--;
    }
    public void addImageUrl(PostImage postImage) {
        this.postImgs.add(postImage);
    }
}
