package Coordinate.coordikittyBE.domain.bookmark.controller;

import Coordinate.coordikittyBE.domain.bookmark.dto.BookmarkResponseDto;
import Coordinate.coordikittyBE.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;
    @GetMapping(value = "/list")
    public ResponseEntity<List<BookmarkResponseDto>> getList(@RequestBody String email) {
        return ResponseEntity.ok().body(bookmarkService.getBookmarksAll(email));
    }
}
