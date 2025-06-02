package com.lexoread.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lexoread.backend.model.Books;
import com.lexoread.backend.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public List<Books> getBooks(@RequestParam Long userId, int limit, int offset) {
        return service.findBooks(userId, limit, offset);
    }


    @GetMapping("/{id}")
    public Books getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Books create(@RequestBody Books books) {
        return service.save(books);
    }

    @PutMapping("/{id}")
    public Books update(@PathVariable Long id, @RequestBody Books books) {
        return service.update(id, books);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
