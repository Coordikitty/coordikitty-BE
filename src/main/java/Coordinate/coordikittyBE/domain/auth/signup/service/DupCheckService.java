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
        return userRepository.findById(email).isEmpty();
    }

    public Boolean nicknameDupCheck(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }
}
