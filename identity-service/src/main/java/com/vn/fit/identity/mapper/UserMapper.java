package com.vn.fit.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vn.fit.identity.dto.request.UserCreationRequest;
import com.vn.fit.identity.dto.request.UserUpdateRequest;
import com.vn.fit.identity.dto.response.UserResponse;
import com.vn.fit.identity.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
