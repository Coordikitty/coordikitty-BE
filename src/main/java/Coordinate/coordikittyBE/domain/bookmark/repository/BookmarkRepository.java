package Coordinate.coordikittyBE.domain.bookmark.repository;

import Coordinate.coordikittyBE.domain.bookmark.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, UUID> {
}
