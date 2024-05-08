package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.domain.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}
