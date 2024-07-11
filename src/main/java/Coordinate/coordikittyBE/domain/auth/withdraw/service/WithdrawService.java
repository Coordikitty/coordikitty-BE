package Coordinate.coordikittyBE.domain.auth.withdraw.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    public String withdraw(String email) {
        userRepository.deleteById(email);
        refreshTokenRepository.deleteByUserId(email);
        return "삭제 성공";
    }
}
