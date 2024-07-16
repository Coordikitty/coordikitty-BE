package Coordinate.coordikittyBE.backoffice.user.controller;

import Coordinate.coordikittyBE.backoffice.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @DeleteMapping("/all-but-one")
    public ResponseEntity<String> allButOne() {
        return ResponseEntity.ok(adminUserService.allButOne());
    }

    @DeleteMapping("/delete-cloth-image")
    public ResponseEntity<String> deleteClothImage() {
        return ResponseEntity.ok(adminUserService.deleteInFirebaseWithClothImageUrl());
    }

    @DeleteMapping("/delete-post-image")
    public ResponseEntity<String> deletePostImage() {
        return ResponseEntity.ok(adminUserService.deleteInFirebaseWithPostImageUrl());
    }

    @PostMapping("/create-id")
    public ResponseEntity<String> createId() {
        System.out.println("create-id");
        return ResponseEntity.ok(adminUserService.createId());
    }

    @PostMapping("/copy-email")
    public ResponseEntity<String> copyEmail() {
        return ResponseEntity.ok(adminUserService.copyEmail());
    }
}
