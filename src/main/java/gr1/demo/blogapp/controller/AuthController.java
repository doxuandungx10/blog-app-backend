package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.LoginRequest;
import gr1.demo.blogapp.dto.RegisterRequest;
import gr1.demo.blogapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity singup(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest.getEmail());
        authService.signup(registerRequest);

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
