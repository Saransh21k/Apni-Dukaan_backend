package com.apni.dukaan.service;

import com.apni.dukaan.dto.RegisterRequest;
import com.apni.dukaan.dto.request.LoginRequest;
import com.apni.dukaan.dto.response.AuthResponse;

public interface UserService {
    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
