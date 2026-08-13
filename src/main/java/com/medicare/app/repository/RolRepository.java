package com.medicare.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.app.domain.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
