package com.moing.bo.backend.domain.admin.domain.entity;

import com.moing.bo.backend.domain.admin.domain.constant.ApprovedStatus;
import com.moing.bo.backend.domain.admin.domain.constant.Role;
import com.moing.bo.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Admin extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovedStatus approvedStatus;

    private LocalDateTime lastLogInDate;

    public boolean isApproved() {
        return this.approvedStatus.equals(ApprovedStatus.APPROVED);
    }

    public void updateLogInDate() {
        this.lastLogInDate = LocalDateTime.now();
    }

    public void changeAccounts(boolean isApproved) {
        if (isApproved) {
            this.approvedStatus = ApprovedStatus.APPROVED;
        }
        this.approvedStatus = ApprovedStatus.REJECTED;
    }

    public void encodePassword(String password){
        this.password=password;
    }
}
