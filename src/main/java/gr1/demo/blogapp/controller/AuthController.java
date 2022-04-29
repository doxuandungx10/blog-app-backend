package gr1.demo.blogapp.controller;

import gr1.demo.blogapp.dto.RegisterRequest;
import gr1.demo.blogapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity singup(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest.getEmail());
        authService.signup(registerRequest);

        return ResponseEntity.ok(registerRequest);

    }
}
