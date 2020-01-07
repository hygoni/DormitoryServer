package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.advice.exception.LoginException;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.model.SingleResult;
import com.github.hygoni.dormitory.model.User;
import com.github.hygoni.dormitory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ResponseService responseService;

    public Optional<User> findUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public void deleteUserByUid(String uid) {
        userRepository.deleteUserById(uid);
    }

    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
