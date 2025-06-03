package com.lexoread.backend.service;

import com.lexoread.backend.model.Book;
import com.lexoread.backend.model.User;

import com.lexoread.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll(int limit, int offset) {
        return repo.findUsersWithLimitOffset(limit, offset);
    }

    public User findById(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + id));
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with username: " + username));
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with email: " + email));
    }

    public User save(User user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repo.save(user);
    }

    public User update(Long id, User updatedUser) {
        User existingUser = findById(id);

        if (!existingUser.getEmail().equals(updatedUser.getEmail()) &&
                repo.existsByEmail(updatedUser.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());

        return repo.save(existingUser);
    }

    public void delete(Long id) {
        User user = findById(id);
        repo.delete(user);
    }

    public List<Book> getUserBooks(Long userId) {
        User user = findById(userId);
        return user.getBooks();
    }
}
