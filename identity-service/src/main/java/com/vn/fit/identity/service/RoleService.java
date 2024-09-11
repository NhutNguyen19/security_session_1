package com.vn.fit.identity.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.fit.identity.dto.request.RoleRequest;
import com.vn.fit.identity.dto.response.RoleResponse;
import com.vn.fit.identity.mapper.RoleMapper;
import com.vn.fit.identity.model.Permission;
import com.vn.fit.identity.model.Role;
import com.vn.fit.identity.repository.PermissionRepository;
import com.vn.fit.identity.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        Role roles = roleMapper.toRole(request);
        HashSet<Permission> permissions = new HashSet<>(permissionRepository.findAllById(request.getPermissions()));
        roles.setPermissions(permissions);
        return roleMapper.toRoleResponse(roleRepository.save(roles));
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
