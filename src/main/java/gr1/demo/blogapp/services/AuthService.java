package gr1.demo.blogapp.services;

import gr1.demo.blogapp.dto.RegisterRequest;
import gr1.demo.blogapp.model.User;
import gr1.demo.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUserName(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
    }
}
