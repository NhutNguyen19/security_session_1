package com.vn.fit.identity.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vn.fit.identity.dto.request.UserCreationRequest;
import com.vn.fit.identity.dto.response.UserResponse;
import com.vn.fit.identity.service.UserService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    UserCreationRequest request;
    UserResponse userResponse;
    LocalDate dob;

    @BeforeEach
    public void initData() {
        dob = LocalDate.of(2002, 5, 19);
        request = UserCreationRequest.builder()
                .username("nhut15")
                .password("123456")
                .email("nhut@gmail.com")
                .build();
        userResponse = UserResponse.builder()
                .id("131-sada-asdad")
                .username("nhut15")
                .email("nhut@gmail.com")
                .build();
    }

    @Test
    public void createUser_validRequest_success() throws Exception {
        log.info("Create user test success");
        // GIVE
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String context = objectMapper.writeValueAsString(request);
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);
        // WHEN, //THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(context))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("131-sada-asdad"));
    }

    @Test
    public void createUser_invalidRequest_success() throws Exception {
        log.info("Create user test fail");
        // GIVE
        request.setUsername("nhu");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String context = objectMapper.writeValueAsString(request);
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);
        // WHEN, //THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(context))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1003))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 4 characters"));
    }
}
