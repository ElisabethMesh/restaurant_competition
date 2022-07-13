package com.meshalkina.restaurant_competition.service;


import com.meshalkina.restaurant_competition.model.Role;
import com.meshalkina.restaurant_competition.model.Status;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.repository.UserRepository;
import com.meshalkina.restaurant_competition.util.UserUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getByIdUser(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public User updateUser(User user) {
        User currentUser = UserUtil.getCurrentUser();
        User fromDB = userRepository.findById(user.getId()).orElse(null);
        if (user.getUsername() == null) {
            user.setUsername(fromDB.getUsername());
        }
        if (user.getPassword() == null) {
            user.setPassword(fromDB.getPassword());
        }
        if (user.getPassword() != fromDB.getPassword()) {
            String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        if (user.getFirstname() == null) {
            user.setFirstname(fromDB.getFirstname());
        }
        if (user.getLastname() == null) {
            user.setLastname(fromDB.getLastname());
        }
        if (currentUser.getRole() == Role.USER) {
            if (fromDB.getStatus() != user.getStatus()) {
                user.setStatus(fromDB.getStatus());
            }
        }
        if (user.getStatus() == null) {
            user.setStatus(fromDB.getStatus());
        }
        if (currentUser.getRole() == Role.USER) {
            if (fromDB.getRole() != user.getRole()) {
                user.setRole(currentUser.getRole());
            }
        }
        if (user.getRole() == null) {
            user.setRole(fromDB.getRole());
        }
        user.setCreated(fromDB.getCreated());
        user.setUpdated(LocalDateTime.now());

        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
