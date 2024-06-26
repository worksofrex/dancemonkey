package com.benitha.NE.auth;

import com.benitha.NE.auth.exceptions.UsernameTakenException;
import com.benitha.NE.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication and authorization")
@Hidden
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDTO dto) {

        return ResponseEntity.ok(authService.register(dto));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }



    @ExceptionHandler(value = UsernameTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExistsException(UsernameTakenException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

}
