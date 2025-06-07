package com.lexoread.backend.controller;

import com.lexoread.backend.model.Comment;
import com.lexoread.backend.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comment Management", description = "API для управления комментариями")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @Operation(summary = "Получить список комментариев", description = "Возвращает пагинированный список")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры пагинации")
    })
    @GetMapping
    public List<Comment> getComments(
            @Parameter(description = "Количество записей на странице", example = "10")
            @RequestParam(defaultValue = "10") int limit,
            @Parameter(description = "Смещение (номер страницы)", example = "0")
            @RequestParam(defaultValue = "0") int offset) {
        return service.findAll(limit, offset);
    }

    @Operation(summary = "Получить комментарий по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий найден",
                    content = @Content(schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    })
    @GetMapping("/{id}")
    public Comment getById(
            @Parameter(description = "ID комментария", required = true, example = "1")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Создать новый комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public Comment create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные комментария",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Comment.class)))
            @RequestBody Comment comment) {
        return service.save(comment);
    }

    @Operation(summary = "Обновить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий обновлен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    })
    @PutMapping("/{id}")
    public Comment update(
            @Parameter(description = "ID комментария", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новые данные комментария",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Comment.class)))
            @RequestBody Comment comment) {
        return service.update(id, comment);
    }

    @Operation(summary = "Удалить комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Комментарий удален"),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    })
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID комментария", required = true, example = "1")
            @PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Получить ответы на комментарий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список ответов"),
            @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    })
    @GetMapping("/{commentId}/replies")
    public List<Comment> getCommentReplies(
            @Parameter(description = "ID комментария", required = true, example = "1")
            @PathVariable Long commentId) {
        return service.getReplies(commentId);
    }
}
