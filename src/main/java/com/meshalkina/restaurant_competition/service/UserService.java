package com.meshalkina.restaurant_competition.service;


import com.meshalkina.restaurant_competition.model.Role;
import com.meshalkina.restaurant_competition.model.Status;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
        user.setPassword(encodedPassword);

        LocalDateTime dateTime = LocalDateTime.now();
        user.setCreated(dateTime);
        user.setUpdated(dateTime);

        User newUser = userRepository.save(user);
        return newUser;
    }
}
