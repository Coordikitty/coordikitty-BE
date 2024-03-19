package com.example.coordikittyBE.closet.entity;

import com.example.coordikittyBE.auth.entity.UserEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "cloth")
public class ClothEntity {
    private UUID clothId;
    private String category;
    private String pictureURL;
    private UserEntity userEntity;
}
