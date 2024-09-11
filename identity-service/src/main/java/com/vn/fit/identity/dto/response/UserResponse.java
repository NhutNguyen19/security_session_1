package com.vn.fit.identity.dto.response;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    String id;
    String username;
    String email;
    boolean emailVerified;
    Set<RoleResponse> roles;
}
