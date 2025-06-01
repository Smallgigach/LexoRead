package com.lexoread.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lexoread.backend.model.Book;
import com.lexoread.backend.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> findAll() {
        return repo.findAll();
    }

    public Book findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Book save(Book book) {
        return repo.save(book);
    }

    public Book update(Long id, Book newBook) {
        Book book = findById(id);
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setURLpdf(newBook.getURLpdf());
        return repo.save(book);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
