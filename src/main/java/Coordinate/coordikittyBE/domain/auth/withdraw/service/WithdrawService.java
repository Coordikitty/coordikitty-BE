package Coordinate.coordikittyBE.domain.auth.withdraw.service;

import Coordinate.coordikittyBE.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final UserRepository userRepository;

    @Transactional
    public void withdraw(String email) {
        userRepository.deleteByEmail(email);
    }
}
