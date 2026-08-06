package com.apni.dukaan.service;

import com.apni.dukaan.dto.RegisterRequest;
import com.apni.dukaan.dto.request.LoginRequest;
import com.apni.dukaan.dto.response.AuthResponse;
import com.apni.dukaan.entity.User;
import com.apni.dukaan.exception.EmailAlreadyExistsException;
import com.apni.dukaan.repository.UserRepository;
import com.apni.dukaan.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public void register(RegisterRequest request) {
//        if(userRepository.existsByEmail(request.getEmail())){
//            throw new RuntimeException("Email already exists");
//        }
        System.out.println("Inside register");
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .build();


        userRepository.save(user);
    }
    @Override
    public AuthResponse login(LoginRequest request) {

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // Check if account is enabled
        if (!user.getEnabled()) {
            throw new RuntimeException("Account is disabled");
        }
        String token = jwtService.generateToken(user.getEmail());
        // Login successful
        return AuthResponse.builder()
                .message("Login Successful")
                .token(token)
                .build();
    }
}
