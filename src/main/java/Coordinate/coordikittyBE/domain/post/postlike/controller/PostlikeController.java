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
    private final PostlikeService postlikeService;

    @PostMapping("like")
    public ResponseEntity<String> like(
            @RequestHeader("Authorization") String token,
            @RequestBody UUID postId
    ){
        try{
            return postlikeService.like(postId);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("like")
    public ResponseEntity<String> dislike(
            @RequestHeader("Authorization") String token,
            @RequestBody UUID postId
    )
    {
        try{
            return postlikeService.dislike(postId);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
