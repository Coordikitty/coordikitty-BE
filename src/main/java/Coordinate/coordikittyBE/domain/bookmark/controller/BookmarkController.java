package Coordinate.coordikittyBE.domain.bookmark.controller;

import Coordinate.coordikittyBE.domain.bookmark.dto.BookmarkResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @GetMapping(value = "/list")
    public ResponseEntity<List<BookmarkResponseDto>> getList(@RequestHeader("Authorization") String token) {
        // 토큰 확인
        // user Entity : user id 반환
        // history Entity : user id가 일치하며, is_bookmarked = true 인 post id 반환
        // post Entity : post id로 검색해서 반환
        List<BookmarkResponseDto> bookmarkResponseDtos = new ArrayList<>();
        return ResponseEntity.ok().body(bookmarkResponseDtos);
    }
}
