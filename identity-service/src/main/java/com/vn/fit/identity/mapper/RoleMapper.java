package com.vn.fit.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vn.fit.identity.dto.request.RoleRequest;
import com.vn.fit.identity.dto.response.RoleResponse;
import com.vn.fit.identity.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
