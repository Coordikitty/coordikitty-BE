package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.domain.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostImageRepository extends JpaRepository<PostImage, UUID> {
    Optional<PostImage> findByPostId(UUID id);
}
