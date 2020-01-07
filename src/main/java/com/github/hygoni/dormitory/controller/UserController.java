package com.github.hygoni.dormitory.controller;

import com.github.hygoni.dormitory.advice.exception.LoginException;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.model.SingleResult;
import com.github.hygoni.dormitory.model.User;
import com.github.hygoni.dormitory.security.JwtTokenProvider;
import com.github.hygoni.dormitory.service.ResponseService;
import com.github.hygoni.dormitory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;
    private ResponseService responseService;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public CommonResult register(@RequestParam String uid, @RequestParam String password,
                                 @RequestParam String gender, @RequestParam int buildingNumber) {
        userService.save(User.builder()
        .uid(uid)
        .password(passwordEncoder.encode(password))
        .gender(gender)
        .buildingNumber(buildingNumber)
        .build()
        );

        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public SingleResult<String> login(@RequestParam String uid, @RequestParam String password){
        User user = userService.findUserByUid(uid).orElseThrow(LoginException::new);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new LoginException();
        }
        
        //토큰을 생성하여 SingleResult로 전송 "data" : "token"
        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
    }
}
