package com.lexoread.backend.repository;

import com.lexoread.backend.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.id LIMIT :limit OFFSET :offset")
    List<User> findUsersWithLimitOffset(@Param("limit") int limit, @Param("offset") int offset);

    boolean existsByEmail(@Email @NotBlank String email);
}