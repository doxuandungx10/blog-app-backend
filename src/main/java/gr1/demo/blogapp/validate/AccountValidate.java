package gr1.demo.blogapp.validate;

import org.springframework.stereotype.Service;


public interface AccountValidate {
    boolean isValidPassword(String password);
    boolean isValidUsername(String username);
}
