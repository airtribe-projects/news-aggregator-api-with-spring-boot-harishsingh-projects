package com.example.news_aggregator.auth;

import com.example.news_aggregator.exception.InvalidCredentialsException;
import com.example.news_aggregator.exception.UserAlreadyExistsException;
import com.example.news_aggregator.security.JwtService;
import com.example.news_aggregator.user.User;
import com.example.news_aggregator.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        userService.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Username already exists");
                });

        userService.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Email already exists");
                });

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        userService.save(user);
    }

    public String login(LoginRequest request) {

        User user = userService.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid username or password"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}
