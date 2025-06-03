package com.lexoread.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lexoread.backend.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query(value = "SELECT * FROM books LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Book> findBooksWithLimitOffset(@Param("limit") int limit, @Param("offset") int offset);

}
