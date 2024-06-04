package Coordinate.coordikittyBE.domain.auth.withdraw.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final UserRepository userRepository;

    public String withdraw(String email) {
//        User user = userRepository.findById(email).orElseThrow(()-> new IllegalArgumentException("user not found"));
        userRepository.deleteById(email);
        return "삭제 성공";
    }
}
