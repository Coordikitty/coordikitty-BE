package Coordinate.coordikittyBE.domain.auth.login.service;

import Coordinate.coordikittyBE.domain.auth.entity.UserEntity;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
