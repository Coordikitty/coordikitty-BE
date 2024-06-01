package Coordinate.coordikittyBE.domain.post.posting.controller;


import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUpdateRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostlistResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @GetMapping(value = "")
    public ResponseEntity<?> getPosts(
            //@RequestParam(value = "page") int page,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        try {
            return ResponseEntity.ok(postingService.getPosts(userDetails));
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
    public ResponseEntity<PostResponseDto> getPostListByPostId(
            @PathVariable("postId") UUID postId
    ){
        //게시글 찾기
        return ResponseEntity.ok(postingService.findById(postId));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPost(
            @RequestBody PostUploadRequestDto postUploadRequestDto
//            @AuthenticationPrincipal UserDetails userDetails
    ){
        // upload 수정 필요
        // 1. upload 할 이미지 Dto 에서 따로 분리해서 받기
        // 2. post upload 시에 설정해준 clothId, postId attach 에 등록 후 post Entity 에 attach 등록?? 고민 해보자
        postingService.upload(postUploadRequestDto, "lth8905@gmail.com");
        return ResponseEntity.ok("게시글 업로드 성공");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(
            @RequestBody UUID postId
    ){
        postingService.delete(postId);
        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
