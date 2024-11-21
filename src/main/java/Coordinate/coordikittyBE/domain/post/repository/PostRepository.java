package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.domain.post.entity.Post;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findAllByOrderByLikeCountDesc();

    List<Post> findAllByUserId(UUID userId);


    @Query("SELECT p FROM post p WHERE p.user.email = :email ORDER BY p.createdAt DESC")
    List<Post> findAllByEmailOrderByCreatedAtDesc(@Param("email") String email);
}
