package gr1.demo.blogapp.validate;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class AcountValidateImpl implements AccountValidate {
    /*
        Username bao gồm :
            Các ký tự chữ và số (a-zA-Z0-9), chữ thường hoặc chữ hoa.
            Được phép có dấu chấm (.), Dấu gạch dưới (_) và dấu gạch ngang (-).
            Dấu chấm (.), Dấu gạch dưới (_) hoặc dấu gạch ngang (-) không được là ký tự đầu tiên hoặc cuối cùng.
            Dấu chấm (.), Dấu gạch dưới (_) hoặc dấu gạch ngang (-) không xuất hiện liên tiếp,
            Số lượng ký tự phải từ 5 đến 20.
         */
    private static final String USERNAME_PATTERN= "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){0,20}[a-zA-Z0-9]$";
    private static final Pattern user_pattern = Pattern.compile(USERNAME_PATTERN);

    /*
    Password bao gồm:
        Phải có chữ hoa [A-Z],
        chữ thường [a-z], số [\\d],
        độ dài từ 8-20
     */
    private static final String PASSWORD_PATTERN="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
    private static final Pattern pass_pattern = Pattern.compile(PASSWORD_PATTERN);
    @Override
    public boolean isValidPassword(String password) {
        Matcher matcher = pass_pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public boolean isValidUsername(String username) {
        Matcher matcher = user_pattern.matcher(username);
        return matcher.matches();
    }
}
