package com.meshalkina.restaurant_competition.rest;

import com.meshalkina.restaurant_competition.dto.UserDTO;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {this.userService = userService;}

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user);
        User newUser = userService.createUser(user);
        System.out.println(newUser);
        if(newUser==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUserDTO = userService.getAllUsers()
                .stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allUserDTO, HttpStatus.OK);
    }
}
