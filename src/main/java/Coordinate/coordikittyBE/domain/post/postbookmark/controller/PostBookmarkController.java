package Coordinate.coordikittyBE.domain.post.postbookmark.controller;

import Coordinate.coordikittyBE.domain.post.postbookmark.service.PostBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostBookmarkController {
    private final PostBookmarkService postBookmarkService;
    @PostMapping("/bookmark")
    public ResponseEntity<?> addBookmark(
            @RequestBody UUID postId,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        return ResponseEntity.ok(postBookmarkService.bookmark(postId, userDetails.getUsername()));
    }
}
