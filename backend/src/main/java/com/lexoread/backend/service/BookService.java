package com.lexoread.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lexoread.backend.model.Books;
import com.lexoread.backend.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }
    public List<Books> findBooks(Long userId, int limit, int offset) {
        List<Books> books = repo.findBooksWithLimitOffset(limit, offset);
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

    public Books findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Books save(Books books) {
        return repo.save(books);
    }

    public Books update(Long id, Books newBooks) {
        Books books = findById(id);
        books.setTitle(newBooks.getTitle());
        books.setAuthor(newBooks.getAuthor());
        books.setURLpdf(newBooks.getURLpdf());
        books.setGenre(newBooks.getGenre());
        return repo.save(books);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
