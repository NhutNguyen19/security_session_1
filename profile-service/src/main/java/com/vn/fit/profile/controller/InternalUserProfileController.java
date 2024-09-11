package com.vn.fit.profile.controller;

import com.vn.fit.profile.dto.ApiResponse;
import com.vn.fit.profile.dto.request.ProfileCreationRequest;
import com.vn.fit.profile.dto.response.UserProfileResponse;
import com.vn.fit.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/internal/users")
    ApiResponse<UserProfileResponse> create(@RequestBody ProfileCreationRequest request){
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.create(request))
                .build();
    }
}
