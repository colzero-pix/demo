package com.jie.repository;

import com.jie.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUserId(int userId);

    boolean existsByUsername(String username);

}
