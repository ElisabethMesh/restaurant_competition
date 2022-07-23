package com.meshalkina.restaurant_competition.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meshalkina.restaurant_competition.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-all-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-all-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class VoteRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void createAndGetVote() throws Exception {
        mockMvc.perform(post("/api/votes/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/votes"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/votes/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void updateVote() throws Exception {
        LocalTime now = LocalTime.now();
        LocalTime timeLimit = LocalTime.parse(TimeUtil.TIME_LIMIT);

        mockMvc.perform(post("/api/votes/1"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/votes/2"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(jsonPath("$.rating").value(now.isAfter(timeLimit) ? 1 : 0));
    }

    @Test
    @WithUserDetails(value = "lisa", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void deleteVoteById() throws Exception {
        mockMvc.perform(post("/api/votes/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/votes/1"))
                .andExpect(status().isOk());
    }
}