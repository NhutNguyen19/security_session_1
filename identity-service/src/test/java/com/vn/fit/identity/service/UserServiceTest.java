package com.vn.fit.identity.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vn.fit.identity.dto.request.UserCreationRequest;
import com.vn.fit.identity.dto.response.UserResponse;
import com.vn.fit.identity.exception.AppException;
import com.vn.fit.identity.model.User;
import com.vn.fit.identity.repository.UserRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    UserCreationRequest request;
    UserResponse userResponse;
    User user;
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
        user = User.builder()
                .id("131-sada-asdad")
                .username("nhut15")
                .email("nhut@gmail.com")
                .build();
    }

    @Test
    public void createUser_valid_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);
        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("131-sada-asdad");
        Assertions.assertThat(response.getUsername()).isEqualTo("nhut15");
    }

    @Test
    public void createUser_invalid_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));
        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
        Assertions.assertThat(exception.getMessage()).isEqualTo("User existed");
    }
}
