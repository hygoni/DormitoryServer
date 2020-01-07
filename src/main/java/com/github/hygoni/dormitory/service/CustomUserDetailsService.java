package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.advice.exception.LoginException;
import com.github.hygoni.dormitory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) {
        return userRepository.findById(Long.parseLong(userPk)).orElseThrow(LoginException::new);
    }
}
