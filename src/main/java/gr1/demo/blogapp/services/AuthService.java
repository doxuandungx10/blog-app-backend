package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.AuthenticationResponse;
import gr1.demo.blogapp.dto.LoginRequest;
import gr1.demo.blogapp.dto.RegisterRequest;
import gr1.demo.blogapp.jwt.JwtProvider;
import gr1.demo.blogapp.model.User;
import gr1.demo.blogapp.repository.UserRepository;
import gr1.demo.blogapp.validate.AccountValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider tokenProvider;
    @Autowired
    private AccountValidate accountValidate;
    @Autowired
    private AuthenticationManager authenticationManager;
    public String signup(RegisterRequest registerRequest) throws Exception{
        User user = new User();
        if(accountValidate.isValidPassword(registerRequest.getPassword())==false
                &&!accountValidate.isValidUsername(registerRequest.getUsername())==false) {
            throw new Exception("Username or password is not validated");
        }else{
            if(!userRepository.findByUserName(registerRequest.getUsername()).isEmpty()){
                throw new Exception("Username is existed");
            }
        }
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return "OK";
    }
    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception{

            String x = loginRequest.getPassword();
            // X??c th???c th??ng tin ng?????i d??ng Request l??n
            if(userRepository.findByUserName(loginRequest.getUsername()).isEmpty()) throw new Exception("Username is not exist");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            // N???u kh??ng x???y ra exception t???c l?? th??ng tin h???p l???
            // Set th??ng tin authentication v??o Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Tr??? v??? jwt cho ng?????i d??ng.
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwt,loggedInUser.getName());
            return authenticationResponse;
        }



}
