package Coordinate.coordikittyBE.domain.post.postlike.controller;

import Coordinate.coordikittyBE.domain.post.postlike.service.PostlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostlikeController {
    private final PostlikeService postlikeService;

    @PostMapping("like")
    public ResponseEntity<?> like(
            @RequestBody UUID postId,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        try{
            return ResponseEntity.ok().body(postlikeService.like(postId, userDetails.getUsername()));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("like")
    public ResponseEntity<?> dislike(
            @RequestBody UUID postId,
            @AuthenticationPrincipal UserDetails userDetails
    )
    {
        try{
            return ResponseEntity.ok().body(postlikeService.dislike(postId, userDetails.getUsername()));
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
