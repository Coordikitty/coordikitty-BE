package Coordinate.coordikittyBE.domain.post.posting.controller;


import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.request.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.response.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @GetMapping(value = "/logged")
    public ResponseEntity<?> getPostsLoggedIn(
            //@RequestParam(value = "page") int page,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        try {
            return ResponseEntity.ok(postingService.getPostsLoggedIn(userDetails));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/unLogged")
    public ResponseEntity<?> getPostsUnLoggedIn(
            //@RequestParam(value = "page") int page,
    ){
        try {
            return ResponseEntity.ok(postingService.getPostsUnLoggedIn());
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("postId") UUID postId,
            @RequestBody PostUpdateRequestDto postUpdateRequestDto
    ){
        postingService.update(postId, postUpdateRequestDto);
        return ResponseEntity.ok("게시글 수정 완료");
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<PostResponseDto> getPostByPostId(
            @PathVariable("postId") UUID postId
    ){
        return ResponseEntity.ok(postingService.findById(postId));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPost(
            @RequestPart PostUploadRequestDto postUploadRequestDto,
            @RequestPart List<MultipartFile> postImgs,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        postingService.upload(postUploadRequestDto, postImgs, userDetails.getUsername());
        return ResponseEntity.ok("게시글 업로드 성공");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(
            @RequestParam UUID postId
            ){
        try {
            postingService.delete(postId);
            return ResponseEntity.ok("게시글 삭제 성공");
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
