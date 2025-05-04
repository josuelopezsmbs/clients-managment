package com.seek.client_management.service.impl;

import com.seek.client_management.dto.request.LoginUserRequest;
import com.seek.client_management.dto.request.RegisterUserRequest;
import com.seek.client_management.model.User;
import com.seek.client_management.repository.UserRepository;
import com.seek.client_management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User signup(RegisterUserRequest input) {
        User user = User
                .builder()
                .role(input.role())
                .email(input.email())
                .password(passwordEncoder.encode(input.password()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        return userRepository.findByEmail(input.email())
                .orElseThrow();
    }
}
