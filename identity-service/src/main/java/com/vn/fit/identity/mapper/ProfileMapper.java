package com.vn.fit.identity.mapper;

import org.mapstruct.Mapper;

import com.vn.fit.identity.dto.request.ProfileCreationRequest;
import com.vn.fit.identity.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
