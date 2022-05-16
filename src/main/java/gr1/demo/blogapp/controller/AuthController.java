package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.AuthenticationResponse;
import gr1.demo.blogapp.dto.LoginRequest;
import gr1.demo.blogapp.dto.RegisterRequest;
import gr1.demo.blogapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signup")
    public String singup(@RequestBody RegisterRequest registerRequest) throws Exception {
        authService.signup(registerRequest);
        return "OK";
    }
    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
