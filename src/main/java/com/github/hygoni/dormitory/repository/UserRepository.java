package com.github.hygoni.dormitory.repository;

import com.github.hygoni.dormitory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);
    void deleteUserById(String id);
    User save(User user);
}
