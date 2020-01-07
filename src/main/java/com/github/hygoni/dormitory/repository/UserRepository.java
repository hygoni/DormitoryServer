package com.github.hygoni.dormitory.repository;

import com.github.hygoni.dormitory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(long privateKey);
    void deleteUserById(String id);
    User save(User user);
    User findUserById(String id);
}
