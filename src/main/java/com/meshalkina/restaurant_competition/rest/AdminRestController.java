package com.meshalkina.restaurant_competition.rest;

import com.meshalkina.restaurant_competition.dto.UserDTO;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
@Api("Controller for administrator work")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Create new user")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = UserDTO.fromUser(newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ApiOperation("Get all users")
    @GetMapping()
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUserDTO = userService.getAllUsers()
                .stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allUserDTO, HttpStatus.OK);
    }

    @ApiOperation("Get a user by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getByIdUser(id);
        if (user != null) {
            UserDTO userDTO = UserDTO.fromUser(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Update the user")
    @PutMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        if (updateUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = UserDTO.fromUser(updateUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ApiOperation("Delete the user")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
