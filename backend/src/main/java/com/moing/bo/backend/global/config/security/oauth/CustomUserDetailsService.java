package com.moing.bo.backend.global.config.security.oauth;

import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.admin.domain.service.AdminGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminGetService adminGetService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String logInId) throws UsernameNotFoundException {
        Admin admin = this.adminGetService.getAdminByLoginId(logInId);

        if (admin == null) {
            throw new UsernameNotFoundException("User not found with logInId: " + logInId);
        }

        return new CustomUserDetails(admin);
    }
}

