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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-all-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-all-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class MealRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void createMeal() throws Exception {
        mockMvc.perform(post("/api/meals/3")
                        .content(objectMapper.writeValueAsString(TestRestaurantAndMealUtil.mealForCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pies"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getMealById() throws Exception {
        mockMvc.perform(get("/api/meals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chicken with oranges"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getANonExistentMeal() throws Exception {
        mockMvc.perform(get("/api/meals/7"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void updateMeal() throws Exception {
        mockMvc.perform(put("/api/meals")
                        .content(objectMapper.writeValueAsString(TestRestaurantAndMealUtil.mealForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Coca Cola"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void deleteMeal() throws Exception {
        mockMvc.perform(get("/api/meals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chicken with oranges"));

        mockMvc.perform(delete("/api/meals/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/meals/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void userIsTryingToDeleteMeal() throws Exception {
        mockMvc.perform(delete("/api/meals/1"))
                .andExpect(status().isForbidden());
    }
}