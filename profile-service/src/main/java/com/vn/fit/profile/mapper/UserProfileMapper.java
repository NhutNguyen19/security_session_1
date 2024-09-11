package com.vn.fit.profile.mapper;

import com.vn.fit.profile.dto.request.ProfileCreationRequest;
import com.vn.fit.profile.dto.response.UserProfileResponse;
import com.vn.fit.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
