package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostImageRepository extends JpaRepository<PostImage, UUID> {
    List<PostImage> findAllByPostId(UUID id);
}
