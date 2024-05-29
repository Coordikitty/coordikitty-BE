package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DupCheckService {
    private final UserRepository userRepository;

    public String emailDupCheck(String email) {
        UserEntity user = userRepository.findById(email).orElse(null);
        if (user == null) {
            return "사용가능한 이메일";
        }
        throw new IllegalArgumentException("사용 불가능한 이메일");
    }

    public String nicknameDupCheck(String nickname) {
        UserEntity user = userRepository.findByNickname(nickname).orElse(null);
        if (user == null) {
            return "사용가능한 닉네임";
        }
        throw new IllegalArgumentException("사용 불가능한 닉네임");
    }
}
