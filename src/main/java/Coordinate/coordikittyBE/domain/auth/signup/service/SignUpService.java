package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.auth.signup.dto.SignupRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SignUpService {
    UserRepository userRepository;
    public ResponseEntity<String> signUp(SignupRequestDTO signUpRequestDto) {
        List<String> role= new ArrayList<>();
        role.add("USER");

        UserEntity user = UserEntity.builder()
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .name(signUpRequestDto.getName())
                .nickname(signUpRequestDto.getNickname())
                .age(signUpRequestDto.getAge())
                .birth(signUpRequestDto.getBirth())
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .tall(signUpRequestDto.getTall())
                .weight(signUpRequestDto.getWeight())
                .shoeSize(signUpRequestDto.getFootSize())
                .roles(role)
                .profileUrl(null).alarm_like(null).alarm_feed(null)
                .alarm_follow(null).clothes(null).bookmarks(null)
                .historys(null).followers(null).followings(null)
                .posts(null)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("회원가입 완료");
    }
}
