package com.apni.dukaan.controller;

import com.apni.dukaan.dto.RegisterRequest;
import com.apni.dukaan.dto.request.LoginRequest;
import com.apni.dukaan.dto.response.AuthResponse;
import com.apni.dukaan.dto.response.CategoryResponse;
import com.apni.dukaan.service.CategoryService;
import com.apni.dukaan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        userService.register(request);

        return AuthResponse.builder()
                .message("User Registered Successfully")
                .build();
    }
    @GetMapping("/test")
    public String test() {
        return "Working";
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        System.out.println("Controller reached");
        return userService.login(request);
    }



}
