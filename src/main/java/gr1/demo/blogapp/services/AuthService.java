package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.LoginRequest;
import gr1.demo.blogapp.dto.RegisterRequest;
import gr1.demo.blogapp.jwt.JwtProvider;
import gr1.demo.blogapp.model.User;
import gr1.demo.blogapp.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
    }
    public String login(LoginRequest loginRequest){

            String x = loginRequest.getPassword();
            // Xác thực thông tin người dùng Request lên
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedInUser.getName();
        System.out.println(loggedInUser.getPrincipal());
            String jwt = tokenProvider.generateToken((CustomUserDetails) loggedInUser.getPrincipal());
            return username;
        }


}
