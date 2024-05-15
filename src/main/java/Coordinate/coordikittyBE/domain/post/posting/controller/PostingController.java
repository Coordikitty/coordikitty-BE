package Coordinate.coordikittyBE.domain.post.posting.controller;


import Coordinate.coordikittyBE.domain.post.posting.dto.PostResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostUploadRequestDto;
import Coordinate.coordikittyBE.domain.post.posting.dto.PostlistResponseDto;
import Coordinate.coordikittyBE.domain.post.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
            @RequestHeader("Authorization") String token
    ){
        List<PostlistResponseDto> postlistResponseDtos = new ArrayList<>();
        return ResponseEntity.ok(postlistResponseDtos);
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<PostResponseDto> getPostListByPostId(
            @RequestHeader("Authorization") String token,
            @PathVariable("postId") UUID postId
    ){
        //게시글 찾기
        PostResponseDto postResponseDto = postingService.findById(postId);
        return ResponseEntity.ok(postResponseDto);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPost(
            @RequestHeader("Authorization") String token,
            @RequestBody PostUploadRequestDto postUploadRequestDto
    ){
        postingService.upload(postUploadRequestDto);
        return ResponseEntity.ok("게시글 업로드 성공");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePost(
            @RequestHeader("Authorization") String token,
            @RequestBody UUID postId
    ){
        postingService.delete(postId);
        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
