package com.seek.client_management.service;

import com.seek.client_management.dto.request.LoginUserRequest;
import com.seek.client_management.dto.request.RegisterUserRequest;
import com.seek.client_management.model.User;

public interface AuthService {
    User signup(RegisterUserRequest request);

    User authenticate(LoginUserRequest request);
}
