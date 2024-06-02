package Coordinate.coordikittyBE.domain.auth.signup.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DupCheckService {
    private final UserRepository userRepository;

    public Boolean emailDupCheck(String email) {
        User user = userRepository.findById(email).orElse(null);
        return user == null;
    }

    public Boolean nicknameDupCheck(String nickname) {
        User user = userRepository.findByNickname(nickname).orElse(null);
        return user == null;
    }
}
