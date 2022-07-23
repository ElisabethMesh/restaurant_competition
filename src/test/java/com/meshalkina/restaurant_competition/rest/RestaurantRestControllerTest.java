package com.meshalkina.restaurant_competition.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meshalkina.restaurant_competition.util.TestRestaurantAndMealUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-all-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-all-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RestaurantRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void createRestaurant() throws Exception {
        mockMvc.perform(post("/api/restaurants")
                        .content(objectMapper.writeValueAsString(TestRestaurantAndMealUtil.restaurantForCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.name").value("At grandma's"));
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getAllRestaurants() throws Exception {
        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(TestRestaurantAndMealUtil.getListRestaurantDTO())));
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getRestaurantById() throws Exception {
        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Panda"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getANonExistentRestaurant() throws Exception {
        mockMvc.perform(get("/api/restaurants/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void updateRestaurant() throws Exception {
        mockMvc.perform(get("/api/restaurants/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Donuts"));

        mockMvc.perform(put("/api/restaurants")
                        .content(objectMapper.writeValueAsString(TestRestaurantAndMealUtil.restaurantForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("AWarmPlace"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void deleteRestaurant() throws Exception {
        mockMvc.perform(get("/api/restaurants/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Donuts"));

        mockMvc.perform(delete("/api/restaurants/3"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/restaurants/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void userIsTryingToDeleteRestaurant() throws Exception {
        mockMvc.perform(delete("/api/restaurants/3"))
                .andExpect(status().isForbidden());
    }
}