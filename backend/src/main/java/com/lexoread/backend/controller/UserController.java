//ХАВХЫАХЫВХАЫФХХ
package com.lexoread.backend.controller;

import com.lexoread.backend.model.Book;
import com.lexoread.backend.model.User;
import com.lexoread.backend.service.UserService;
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
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "API для управления пользователями")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Получить список пользователей", description = "Возвращает пагинированный список")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры пагинации")
    })
    @GetMapping
    public List<User> getUsers(
            @Parameter(description = "Количество записей на странице", example = "10")
            @RequestParam(defaultValue = "10") int limit,

            @Parameter(description = "Смещение (номер страницы)", example = "0")
            @RequestParam(defaultValue = "0") int offset) {
        return service.findAll(limit, offset);
    }
    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public User getById(
            @Parameter(description = "ID пользователя", required = true, example = "1")
            @PathVariable Long id) {
        return service.findById(id);

    }
    @Operation(summary = "Создать нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "409", description = "Пользователь уже существует")
    })
    @PostMapping
    public User create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные пользователя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class)))
            @RequestBody User user) {
        return service.save(user);
    }

    @Operation(summary = "Обновить данные пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{id}")
    public User update(
            @Parameter(description = "ID пользователя", required = true, example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новые данные пользователя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class)))
            @RequestBody User user) {
        return service.update(id, user);
    }

    @Operation(summary = "Удалить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID пользователя", required = true, example = "1")
            @PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Получить книги пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список книг"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{userId}/books")
    public List<Book> getUserBooks(
            @Parameter(description = "ID пользователя", required = true, example = "1")
            @PathVariable Long userId) {
        return service.getUserBooks(userId);
    }
}