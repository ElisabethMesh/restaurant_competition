package com.meshalkina.restaurant_competition.util;


import com.meshalkina.restaurant_competition.dto.UserDTO;
import com.meshalkina.restaurant_competition.model.Role;
import com.meshalkina.restaurant_competition.model.Status;
import com.meshalkina.restaurant_competition.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestUserUtil {

    private static final UserDTO userDtoTest1 = new UserDTO(1L, "Lisa", "Meshalkina", Role.ADMIN,
            "18.07.2022 20:00", "19.07.2022 20:00");
    private static final UserDTO userDtoTest2 = new UserDTO(2L, "Igor", "Meshalkin", Role.USER,
            "18.07.2022 21:00", "19.07.2022 21:00");
    private static final UserDTO userDtoTest3 = new UserDTO(3L, "Michael", "Popov", Role.USER,
            "18.07.2022 21:00", "19.07.2022 21:00");

    public static final User userForCreate = new User
            (4L, LocalDateTime.now(), LocalDateTime.now(), "alisa", "alisa", "Alisa",
                    "Somova", Role.USER, Status.ACTIVE);
    public static final User userForUpdate = new User
            (2L, LocalDateTime.of(2022, 07, 18, 21, 00), LocalDateTime.now(), "igor",
                    "igor", "Igoreshka", "Meshalkin", Role.ADMIN, Status.ACTIVE);
    public static final User userForCreateWithDuplicateUsername = new User
            (5L, LocalDateTime.now(), LocalDateTime.now(), "lisa", "lisa", "Lisa",
                    "Rachenko", Role.USER, Status.ACTIVE);

    public static List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDtoTest1);
        userDTOList.add(userDtoTest2);
        userDTOList.add(userDtoTest3);

        return userDTOList;
    }
}
