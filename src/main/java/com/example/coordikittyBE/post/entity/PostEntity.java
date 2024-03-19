package com.example.coordikittyBE.post.entity;

import com.example.coordikittyBE.tag.TagEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {
    @Id
    private UUID postId;
    @OneToMany(mappedBy = "cloth")
    private List<TagEntity> tagList;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;


}
