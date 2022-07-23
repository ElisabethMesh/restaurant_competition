package com.meshalkina.restaurant_competition.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meshalkina.restaurant_competition.util.TestUserUtil;
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
@Sql(value = "/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-user-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AdminRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void createUser() throws Exception {
        mockMvc.perform(post("/api/admin/users")
                        .content(objectMapper.writeValueAsString(TestUserUtil.userForCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.firstname").value("Alisa"));

    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(TestUserUtil.getAllUsers())));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getUserById() throws Exception {
        mockMvc.perform(get("/api/admin/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Igor"))
                .andExpect(jsonPath("$.lastname").value("Meshalkin"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getANonExistentUser() throws Exception {
        mockMvc.perform(get("/api/admin/users/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void updateNameAndRoleForUser() throws Exception {
        mockMvc.perform(get("/api/admin/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Igor"));
        mockMvc.perform(put("/api/admin/users")
                        .content(objectMapper.writeValueAsString(TestUserUtil.userForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Igoreshka"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void deleteUser() throws Exception {
        mockMvc.perform(get("/api/admin/users/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Michael"));

        mockMvc.perform(delete("/api/admin/users/3"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/admin/users/3"))
                .andExpect(status().isNotFound());
    }
}