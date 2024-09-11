package com.vn.fit.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.fit.identity.model.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
