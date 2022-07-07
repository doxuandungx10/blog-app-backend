package gr1.demo.blogapp.services.datafetcher;

import gr1.demo.blogapp.model.User;
import gr1.demo.blogapp.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataFetcher implements DataFetcher<List<User>> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> get(DataFetchingEnvironment environment) {
        return userRepository.findAll();
    }


}
