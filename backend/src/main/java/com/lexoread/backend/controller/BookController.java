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

import com.lexoread.backend.model.Book;
import com.lexoread.backend.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Operations related to books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    @Operation(summary = "Получить список книг пользователя",description = "Возвращает список книг в порялке с пагинацией, в порядке по интересам пользователя")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Список книг успешно получен"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Некорректный запрос"),
    })
    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam Long userId, int limit, int offset) {
        return service.findBooks(userId, limit, offset);
    }

    @Operation(summary = "Получить книгу по ID", description = "Возвращает книгу по её уникальному идентификатору")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Книга успешно найдена"),
        @ApiResponse(responseCode = "404", description = "Книга не найдена")
    })
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return service.findById(id);
    }
    @Operation(summary = "Создать новую книгу", description = "Добавляет новую книгу в систему")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Книга успешно создана"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные книги")
    })
    @PostMapping
    public Book create(@RequestBody Book book) {
        return service.save(book);
    }
    @Operation(summary = "Обновить книгу", description = "Обновляет существующую книгу по её уникальному идентификатору")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Книга успешно обновлена"),
        @ApiResponse(responseCode = "404", description = "Книга не найдена"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные книги")
    })
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return service.update(id, book);
    }
    @Operation(summary = "Удалить книгу", description = "Удаляет книгу по её уникальному идентификатору")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Книга успешно удалена"),
        @ApiResponse(responseCode = "404", description = "Книга не найдена")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
