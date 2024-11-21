package Coordinate.coordikittyBE.domain.post.repository;

import Coordinate.coordikittyBE.domain.post.entity.Post;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Arrays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByOrderByCreatedAtAsc();

    List<Post> findAllByOrderByLikeCountDesc();

    List<Post> findAllByUserId(UUID userId);


    @Query("SELECT p FROM post p JOIN FETCH p.user WHERE p.user.email = :email ORDER BY p.createdAt DESC")
    List<Post> findAllByUserEmailOrderByCreatedAtDesc(@Param("email") String email);

    @Query("SELECT p FROM post p JOIN FETCH p.user JOIN FETCH p.historys h"
        + " WHERE h.user.email = :email AND h.isBookmarked = :isBookmarked ORDER BY p.createdAt DESC")
    List<Post> findAllByEmailAndIsBookmarkedOrderByCreatedAtDesc(
        @Param("email") String email, @Param("isBookmarked") boolean isBookmarked
    );
}
