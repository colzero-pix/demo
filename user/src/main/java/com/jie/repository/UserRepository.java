package com.jie.repository;

import com.jie.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUserId(int userId);

    boolean existsByUsername(String username);

}
