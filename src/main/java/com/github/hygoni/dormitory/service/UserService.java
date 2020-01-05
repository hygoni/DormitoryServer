package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.model.User;
import com.github.hygoni.dormitory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(String id){
       return userRepository.findUserById(id);
    }

    public void deleteUserById(String id){
        userRepository.deleteUserById(id);
    }
}
