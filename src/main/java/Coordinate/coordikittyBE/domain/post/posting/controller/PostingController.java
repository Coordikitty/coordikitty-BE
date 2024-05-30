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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @GetMapping(value = "")
    public ResponseEntity<List<PostlistResponseDto>> getPosts(
            @RequestParam(value = "page") int page,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        // 페이지에 맞는 게시글 반환

        List<PostlistResponseDto> postlistResponseDtos = postingService.getPosts(page, userDetails);
        return ResponseEntity.ok(postlistResponseDtos);
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<String> updatePost(
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
        PostResponseDto postResponseDto = postingService.findById(postId);
        return ResponseEntity.ok(postResponseDto);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPost(
            @RequestBody PostUploadRequestDto postUploadRequestDto
    ){
        postingService.upload(postUploadRequestDto);
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
