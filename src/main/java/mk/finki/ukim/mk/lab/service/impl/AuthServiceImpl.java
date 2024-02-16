package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.CustomException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean credentialsInvalid(String username, String password){
        return username == null || password == null
                || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if(credentialsInvalid(username, password))
            throw new CustomException("Invalid credentials");

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new CustomException("User not found"));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {

        if(credentialsInvalid(username, password))
            throw new CustomException("Invalid credentials");
        if(!password.equals(repeatPassword))
            throw new CustomException("Passwords do not match");

        if(this.userRepository.findByUsername(username).isPresent() ||
        !this.userRepository.findByUsername(username).isEmpty())
            throw new CustomException("User with the same username already exists");

        User user = new User(username, password, name, surname);
        return userRepository.save(user);
    }
}
