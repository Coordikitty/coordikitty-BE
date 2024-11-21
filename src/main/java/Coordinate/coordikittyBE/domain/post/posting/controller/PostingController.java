package Coordinate.coordikittyBE.domain.post.posting.controller;


import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.usecase.PostingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostingController {
    private final PostingUseCase postingUseCase;
    @GetMapping(value = "")
    public ResponseEntity<?> getPostsLoggedIn(
        @AuthenticationPrincipal UserDetails userDetails
            //@RequestParam(value = "page") int page,
    ) {
        return ResponseEntity.ok(postingUseCase.readAllPosts(userDetails.getUsername()));
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("postId") UUID postId,
            @RequestBody PostUpdateRequestDto postUpdateRequestDto
    ) {
        return ResponseEntity.ok(postingUseCase.update(postId, postUpdateRequestDto));
    }

    @GetMapping(value = "/get/{postId}")
    public ResponseEntity<?> getPostByPostId(
            @PathVariable("postId") UUID postId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(postingUseCase.readById(postId, userDetails.getUsername()));
    }

    @GetMapping("/user")
    public ResponseEntity<?> readPostsByUserId(
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(postingUseCase.readByEmail(userDetails.getUsername()));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPost(
            @RequestPart PostUploadRequestDto postUploadRequestDto,
            @RequestPart List<MultipartFile> postImgs,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(postingUseCase.upload(postUploadRequestDto, postImgs, userDetails.getUsername()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(
            @RequestParam UUID postId
    ) {
        postingUseCase.delete(postId);
        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
