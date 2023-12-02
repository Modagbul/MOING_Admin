package com.moing.bo.backend.domain.member.domain.entity;

import com.moing.bo.backend.domain.member.domain.constant.Gender;
import com.moing.bo.backend.domain.member.domain.constant.RegistrationStatus;
import com.moing.bo.backend.domain.member.domain.constant.Role;
import com.moing.bo.backend.domain.member.domain.constant.SocialProvider;
import com.moing.bo.backend.global.entity.BaseTimeEntity;
import com.moing.bo.backend.global.util.AesConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus registrationStatus;

    @Convert(converter = AesConverter.class)
    private String email;

    private String profileImage;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    @Convert(converter = AesConverter.class)
    private String nickName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String introduction;

    private String fcmToken;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean isNewUploadPush;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean isRemindPush;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean isFirePush;

    private boolean isDeleted;

    private LocalDateTime lastSignInTime;

}
