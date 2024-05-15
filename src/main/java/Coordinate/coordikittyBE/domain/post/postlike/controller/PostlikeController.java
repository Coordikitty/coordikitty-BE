package Coordinate.coordikittyBE.domain.post.postlike.controller;

import Coordinate.coordikittyBE.domain.post.postlike.service.PostlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostlikeController {
    PostlikeService postlikeService;

    @PostMapping("like")
    public ResponseEntity<String> like(
            @RequestHeader("Authoriztion") String token,
            @RequestBody UUID postId
    ){
        postlikeService.like(postId);
        return ResponseEntity.ok("좋아요 성공");
    }
    @DeleteMapping("like")
    public ResponseEntity<String> dislike(
            @RequestHeader("Authorization") String token,
            @RequestBody UUID postId
    )
    {
        postlikeService.dislike(postId);
        return ResponseEntity.ok("좋아요 취소");
    }
}
