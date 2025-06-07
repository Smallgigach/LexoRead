package com.lexoread.backend.service;

import com.lexoread.backend.model.Comment;
import com.lexoread.backend.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Пагинированный поиск
    public Page<Comment> findAllWithPagination(int page, int size) {
        return commentRepository.findAllWithPagination(PageRequest.of(page, size));
    }

    // Альтернативный вариант пагинации
    public List<Comment> findAll(int limit, int offset) {
        return commentRepository.findCommentsWithLimitOffset(limit, offset);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(Long id, Comment comment) {
        Comment existingComment = findById(id);
        existingComment.setText(comment.getText());
        // Можно добавить другие поля для обновления
        return commentRepository.save(existingComment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getReplies(Long commentId) {
        return commentRepository.findByRepliedToId(commentId);
    }

    public List<Comment> findByAuthor(Long authorId) {
        return commentRepository.findByAuthorId(authorId);
    }

    public List<Comment> searchByText(String text) {
        return commentRepository.findByTextContainingIgnoreCase(text);
    }

    public List<Comment> getRootComments() {
        return commentRepository.findByRepliedToIsNull();
    }

    public List<Comment> getRecentComments(int count) {
        return commentRepository.findRecentComments(count);
    }

    public Page<Comment> findByAuthorWithPagination(Long authorId, int page, int size) {
        return commentRepository.findByAuthorId(authorId, PageRequest.of(page, size));
    }

    public long countByAuthor(Long authorId) {
        return commentRepository.countByAuthorId(authorId);
    }

    @Transactional
    public void deleteByAuthor(Long authorId) {
        commentRepository.deleteByAuthorId(authorId);
    }

    public List<Comment> findLongComments(int minLength) {
        return commentRepository.findCommentsWithTextLongerThan(minLength);
    }
}
