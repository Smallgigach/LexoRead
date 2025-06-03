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
    public List<Book> findBooks(Long userId, int limit, int offset) {
        List<Book> books = repo.findBooksWithLimitOffset(limit, offset);
//        Map<String, Double> userRatings = getUserInterests(userId);
//
//        // Сортировка по интересам пользователя
//        books.sort((b1, b2) -> {
//            Double rating1 = userRatings.getOrDefault(b1.getGenre(), 0.0);
//            Double rating2 = userRatings.getOrDefault(b2.getGenre(), 0.0);
//            return Double.compare(rating2, rating1);
//        });
        return books;
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
        book.setGenre(newBook.getGenre());
        return repo.save(book);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
