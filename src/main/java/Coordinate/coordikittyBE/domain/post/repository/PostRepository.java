package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findAllByOrderByLikeCountDesc();

    List<Post> findAllByUserId(UUID userId);
}
