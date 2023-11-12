package com.moing.bo.backend.domain.team.domain;

import com.moing.bo.backend.domain.team.domain.constant.ApprovalStatus;
import com.moing.bo.backend.domain.team.domain.constant.Category;
import com.moing.bo.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Category category;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private String profileImgUrl;

    @Column(nullable = false, length = 300)
    private String introduction;

    @Column(nullable = false, length = 100)
    private String promise;

    @Column(nullable = false)
    private Long leaderId;

    private String invitationCode;

    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    private LocalDateTime approvalTime;

    private boolean isDeleted;

    private LocalDateTime deletionTime;

    private Integer numOfMember; //반정규화

    private Integer levelOfFire;

}
