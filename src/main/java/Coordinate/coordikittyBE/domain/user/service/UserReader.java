package Coordinate.coordikittyBE.domain.user.service;

import Coordinate.coordikittyBE.domain.user.entity.User;
import Coordinate.coordikittyBE.domain.user.repository.UserRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User readByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));
    }
}
