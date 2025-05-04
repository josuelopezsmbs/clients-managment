package com.seek.client_management.controller;

import com.seek.client_management.dto.request.LoginUserRequest;
import com.seek.client_management.dto.request.RegisterUserRequest;
import com.seek.client_management.dto.response.LoginUserResponse;
import com.seek.client_management.model.User;
import com.seek.client_management.service.AuthService;
import com.seek.client_management.util.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtility jwtService;

    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest registerUserDto) {
        var registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> authenticate(@RequestBody LoginUserRequest loginUserDto) {
        var authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginUserResponse loginResponse = LoginUserResponse.builder().token(jwtToken).build();

        return ResponseEntity.ok(loginResponse);
    }
}
