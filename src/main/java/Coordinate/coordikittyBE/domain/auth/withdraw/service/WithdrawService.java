package Coordinate.coordikittyBE.domain.auth.withdraw.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    public String withdraw(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CoordikittyException(ErrorType.EMAIL_NOT_FOUND));
        userRepository.deleteById(user.getId());
        refreshTokenRepository.deleteByUserId(user.getId());
        return "삭제 성공";
    }
}
