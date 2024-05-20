package Coordinate.coordikittyBE.domain.post.postbookmark.controller;

import Coordinate.coordikittyBE.domain.post.postbookmark.service.PostBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostBookmarkController {
    private final PostBookmarkService postBookmarkService;
    @PostMapping("/bookmark")
    public ResponseEntity<String> addBookmark(
            @RequestHeader("Authorization") String token,
            @RequestBody UUID postId
    ){
        try{
            return postBookmarkService.addBookmark(postId);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/bookmark")
    public ResponseEntity<String> deleteBookmark(
            @RequestHeader("Authorization") String token,
            @RequestBody UUID postId
    ){
        try{
            return postBookmarkService.deleteBookmark(postId);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
