package com.github.hygoni.dormitory.service;

<<<<<<< HEAD
import com.github.hygoni.dormitory.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final JwtTokenProvider jwtTokenProvider;
    public String getUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String username = jwtTokenProvider.getUserPk(token);
        return username;
    }

=======
public class SecurityService {
>>>>>>> remove unused configuration
}
