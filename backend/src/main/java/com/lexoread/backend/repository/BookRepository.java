package com.lexoread.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lexoread.backend.model.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
	@Query(value = "SELECT * FROM book LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Books> findBooksWithLimitOffset(@Param("limit") int limit, @Param("offset") int offset);

}
