package Coordinate.coordikittyBE.domain.closet.controller;

import Coordinate.coordikittyBE.domain.closet.dto.request.ClosetDeleteRequestDto;
import Coordinate.coordikittyBE.domain.closet.dto.request.ClosetPostRequestDto;
import Coordinate.coordikittyBE.domain.closet.service.ClosetService;
import Coordinate.coordikittyBE.global.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/closet")
@RequiredArgsConstructor
public class ClosetController {

    private final ClosetService closetService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllClothes(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(closetService.getAllClothes(userDetails.getUsername()));
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postCloth(
            @RequestPart("closetPostRequestDto") ClosetPostRequestDto closetPostRequestDto,
            @RequestPart("clothImg") MultipartFile clothImg,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        closetService.postCloth(userDetails.getUsername(), closetPostRequestDto, clothImg);
        return ResponseEntity.ok(SuccessResponse.from("옷 업로드 성공"));
    }

    @PostMapping(value = "/categorization")
    public ResponseEntity<?> clothCategorization(
            @RequestPart("clothImg") MultipartFile clothImg
    ) {
        return ResponseEntity.ok().body(closetService.clothCategorization(clothImg));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<?> deleteCloth(
            @RequestParam(value = "closetDeleteRequestDto") ClosetDeleteRequestDto closetDeleteRequestDto
    ) {
        closetService.deleteCloth(closetDeleteRequestDto.clothId());
        return ResponseEntity.ok().body(SuccessResponse.from("옷 삭제 성공"));
    }
}
