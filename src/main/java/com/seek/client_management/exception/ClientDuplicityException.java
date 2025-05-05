package com.seek.client_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;

public class ClientDuplicityException extends ErrorResponseException {
    public ClientDuplicityException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY), null,
                ErrorResponse.getDefaultDetailMessageCode(ClientDuplicityException.class, null),
                new Object[]{});
    }
}
