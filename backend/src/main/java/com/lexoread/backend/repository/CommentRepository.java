package com.lexoread.backend.repository;

import com.lexoread.backend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c ORDER BY c.id",
            countQuery = "SELECT COUNT(c) FROM Comment c")
    Page<Comment> findAllWithPagination(Pageable pageable);

    @Query(value = "SELECT * FROM comments ORDER BY id LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Comment> findCommentsWithLimitOffset(@Param("limit") int limit, @Param("offset") int offset);

    List<Comment> findByAuthorId(Long authorId);

    List<Comment> findByTextContainingIgnoreCase(String text);

    List<Comment> findByRepliedToIsNull();

    List<Comment> findByRepliedToId(Long parentCommentId);

    boolean existsById(Long id);

    @Query("SELECT c FROM Comment c WHERE LENGTH(c.text) > :minLength")
    List<Comment> findCommentsWithTextLongerThan(@Param("minLength") int minLength);

    @Query(value = "SELECT * FROM comments ORDER BY id DESC LIMIT :count", nativeQuery = true)
    List<Comment> findRecentComments(@Param("count") int count);

    Page<Comment> findByAuthorId(Long authorId, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.author.id = :authorId")
    long countByAuthorId(@Param("authorId") Long authorId);

    void deleteByAuthorId(Long authorId);
}
