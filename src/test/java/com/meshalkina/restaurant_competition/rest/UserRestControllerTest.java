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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-user-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/api/users")
                        .content(objectMapper.writeValueAsString(TestUserUtil.userForCreate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.firstname").value("Alisa"));
    }

    @Test
    void createUserWithDuplicateUsername() throws Exception {
        mockMvc.perform(post("/api/users")
                        .content(objectMapper.writeValueAsString(TestUserUtil.userForCreateWithDuplicateUsername)))
                .andExpect(status().is(415));
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getUser() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Igor"))
                .andExpect(jsonPath("$.lastname").value("Meshalkin"));
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void getAllUsersForUser() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void updateNameAndRoleForUser() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Igor"));
        mockMvc.perform(put("/api/users")
                        .content(objectMapper.writeValueAsString(TestUserUtil.userForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Igoreshka"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    @WithUserDetails(value = "igor", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/users"))
                .andExpect(status().isOk());
    }
}