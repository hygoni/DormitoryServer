package com.github.hygoni.dormitory.controller;

import com.github.hygoni.dormitory.advice.exception.LoginException;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.model.SingleResult;
import com.github.hygoni.dormitory.model.User;
import com.github.hygoni.dormitory.security.JwtTokenProvider;
import com.github.hygoni.dormitory.service.ResponseService;
import com.github.hygoni.dormitory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public CommonResult register(@RequestBody Map<String, String> payload) {
        userService.save(User.builder()
        .uid(payload.get("uid"))
        .password(passwordEncoder.encode(payload.get("password")))
        .gender(payload.get("gender"))
        .buildingNumber(Integer.parseInt(payload.get("building_number")))
        .roles(Collections.singletonList("ROLE_USER"))
        .build()
        );

        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public SingleResult<String> login(@RequestBody Map<String, String> payload){
        User user = userService.findByUid(payload.get("uid")).orElseThrow(LoginException::new);
        if(!passwordEncoder.matches(payload.get("password"), user.getPassword())){
            throw new LoginException();
        }
        
        //토큰을 생성하여 SingleResult로 전송 "data" : "token"
        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    }

    @PostMapping("/getUser")
    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return userService.findByUid(id);
    }

    @PostMapping("/test")
    public Optional<User> test(@RequestBody Map<String, String> payload) {
        return userService.findByUid(payload.get("uid"));
    }
}
