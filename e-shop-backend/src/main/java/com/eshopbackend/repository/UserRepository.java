package com.eshopbackend.repository;

import com.eshopbackend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    @Transactional
    Optional<User> deleteByUsername(String username);

    boolean existsByUsername(String username);
}
