package com.vn.fit.identity.mapper;

import org.mapstruct.Mapper;

import com.vn.fit.identity.dto.request.PermissionRequest;
import com.vn.fit.identity.dto.response.PermissionResponse;
import com.vn.fit.identity.model.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
