package com.lexoread.backend.controller;

import com.lexoread.backend.dto.AuthRequest;
import com.lexoread.backend.dto.AuthResponce;
import com.lexoread.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // простая проверка логина и пароля (вместо подключения к БД)
        if ("user".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            String token = jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные данные");
        }
    }
}
