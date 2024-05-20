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
        //레포지토리에서 북마크 찾고 -> 안에 게시글리스트들 쫙 있을거고 맞나(?) 그러면 for each문으로 싹돌면서 userid, postid 검색해서 가져온다.)가 되겠네요
        List<BookmarkResponseDto> bookmarkResponseDtos = new ArrayList<>();
        return ResponseEntity.ok().body(bookmarkResponseDtos);
    }
}
