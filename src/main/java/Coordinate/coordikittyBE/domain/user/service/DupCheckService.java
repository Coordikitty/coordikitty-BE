package Coordinate.coordikittyBE.domain.user.service;

import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DupCheckService {
    private final UserRepository userRepository;

    public Boolean emailDupCheck(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public Boolean nicknameDupCheck(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }
}
