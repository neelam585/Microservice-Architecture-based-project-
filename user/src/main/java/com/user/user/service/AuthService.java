package com.user.user.service;

import com.user.user.dto.LoginRequest;
import com.user.user.model.User;
import com.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;

    public String authenticate(LoginRequest request) {

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user);

    }
    // Register new user
    public User register(User user) {
        return repository.save(user);
    }
    // Delete user
    public boolean deleteUserById(Long userId){
        repository.deleteById(userId);
        return false;
    }

}
