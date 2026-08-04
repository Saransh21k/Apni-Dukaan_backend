package com.apni.dukaan.service;

import com.apni.dukaan.dto.RegisterRequest;
import com.apni.dukaan.entity.User;
import com.apni.dukaan.exception.EmailAlreadyExistsException;
import com.apni.dukaan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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
}
