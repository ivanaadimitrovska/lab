package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.lab.model.exception.InvalidUser;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository inMemoryUserRepository;

    public AuthServiceImplementation(UserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return inMemoryUserRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUser::new);
    }
}
