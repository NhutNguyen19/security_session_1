package com.vn.fit.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.fit.identity.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
