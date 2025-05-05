package com.seek.client_management.controller;

import com.seek.client_management.dto.request.LoginUserRequest;
import com.seek.client_management.dto.request.RegisterUserRequest;
import com.seek.client_management.dto.response.LoginUserResponse;
import com.seek.client_management.model.User;
import com.seek.client_management.service.AuthService;
import com.seek.client_management.util.JwtUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthController {

    private final JwtUtility jwtService;

    private final AuthService authenticationService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserRequest registerUserDto) {
        var registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @Operation(summary = "Authenticate an existing user and return JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful, JWT token returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginUserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> authenticate(@Valid @RequestBody LoginUserRequest loginUserDto) {
        var authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginUserResponse loginResponse = LoginUserResponse.builder().token(jwtToken).build();

        return ResponseEntity.ok(loginResponse);
    }
}
