package com.example.coordikittyBE.closet.entity;

import com.example.coordikittyBE.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "cloth")
public class ClothEntity {
    @Id
    @Column(name = "cloth_id", nullable = false)
    private UUID clothId;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "pictureURL", nullable = false)
    private String pictureURL;

    @ManyToOne  // Many = Cloth, One = User
    @JoinColumn(name = "email", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;


}