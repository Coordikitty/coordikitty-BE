package Coordinate.coordikittyBE.domain.post.entity;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.closet.enums.Style;
import Coordinate.coordikittyBE.domain.history.entity.History;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "post")
public class Post {
    @Id
    @Column(name="id", nullable = false)
    private UUID id;

    @Column(name="like_Count", nullable = false)
    private int likeCount;

    @Column(columnDefinition = "TEXT", name="content")
    private String content;

    @Column(name = "style", nullable = true)
    private Style style;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> postImgs = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Attach> attaches = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<History> historys = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(PostUpdateRequestDto postUpdateRequestDto, List<Attach> attaches) {
        attaches.clear();
        this.content = postUpdateRequestDto.getContent();
        this.style = postUpdateRequestDto.getStyle();
        this.modifiedAt = LocalDateTime.now();
        this.attaches = attaches;
    }

    public void like(){
        this.likeCount++;
    }
    public void unlike(){
        this.likeCount--;
    }
    public void addImageUrl(String imageUrl) {
        this.postImgs.add(imageUrl);
    }
}
