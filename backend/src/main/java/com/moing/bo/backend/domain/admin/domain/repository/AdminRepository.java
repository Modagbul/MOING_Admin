package com.moing.bo.backend.domain.admin.domain.repository;

import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByLoginId(String logInId);
}
