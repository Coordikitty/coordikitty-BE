package Coordinate.coordikittyBE.domain.post.postbookmark.controller;

import Coordinate.coordikittyBE.domain.post.postbookmark.usecase.PostBookmarkUseCase;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostBookmarkController {
    private final PostBookmarkUseCase postBookmarkUseCase;
    @PostMapping("/bookmark")
    public ResponseEntity<?> addBookmark(
            @RequestBody UUID postId,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        return ResponseEntity.ok(
            SuccessResponse.from(postBookmarkUseCase.toggleBookmark(postId, userDetails.getUsername())));
    }

    @GetMapping("/user/bookmark")
    public ResponseEntity<?> readBookmarkedPostsByUserId(
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(postBookmarkUseCase.findByEmailAndBookmark(userDetails.getUsername()));
    }
}
