package com.meshalkina.restaurant_competition.util;

import com.meshalkina.restaurant_competition.model.User;
import com.meshalkina.restaurant_competition.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private static UserService userService;

    public UserUtil(UserService userService) {
        UserUtil.userService = userService;
    }

    public static User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userService.getByUsername(username);

        return currentUser;
    }
}
