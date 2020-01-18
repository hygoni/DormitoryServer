package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.advice.exception.UserNotFoundException;
import com.github.hygoni.dormitory.model.User;
import com.github.hygoni.dormitory.repository.UserRepository;
import com.github.hygoni.dormitory.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    public String getUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String username = jwtTokenProvider.getUserPk(token);
        return username;
    }
}
