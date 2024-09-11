package com.vn.fit.identity.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.vn.fit.identity.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

    String firstName;
    String lastName;

    @Email(message = "INVALID_EMAIL")
    String email;

    boolean emailVerified;

    @Pattern(regexp = "\\d{10}", message = "INVALID_PHONE_NUMBER")
    String phone;

    @DobConstraint(min = 10, message = "INVALID_DATE_OF_BIRTH")
    LocalDate dob;

    String city;
}
