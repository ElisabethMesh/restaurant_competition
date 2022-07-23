package com.meshalkina.restaurant_competition.rest;

import com.meshalkina.restaurant_competition.dto.UserDTO;
import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.service.UserService;
import com.meshalkina.restaurant_competition.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Api("Controller for user work")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
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

    @ApiOperation("Get the current user")
    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<User> getUser() {
        User user = UserUtil.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @ApiOperation("Update the current user")
    @PutMapping
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        if (updateUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = UserDTO.fromUser(updateUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ApiOperation("Delete the current user")
    @DeleteMapping
    @PreAuthorize("hasAuthority('users:read')")
    public void deleteUser() {
        userService.deleteUser(UserUtil.getCurrentUser().getId());
    }
}
