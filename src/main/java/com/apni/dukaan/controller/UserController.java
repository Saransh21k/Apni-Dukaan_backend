package com.apni.dukaan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/v1/user/profile")
    public String profile() {
        return "Welcome to your profile!";
    }
}