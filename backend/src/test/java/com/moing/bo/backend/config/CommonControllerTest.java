package com.moing.bo.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moing.bo.backend.domain.admin.domain.constant.Role;
import com.moing.bo.backend.domain.admin.domain.entity.Admin;
import com.moing.bo.backend.domain.admin.domain.service.AdminGetService;
import com.moing.bo.backend.domain.team.domain.constant.ApprovalStatus;
import com.moing.bo.backend.global.config.security.filter.JwtAccessDeniedHandler;
import com.moing.bo.backend.global.config.security.filter.JwtAuthenticationEntryPoint;
import com.moing.bo.backend.global.config.security.jwt.TokenUtil;
import com.moing.bo.backend.global.config.security.util.AuthenticationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@AutoConfigureRestDocs
@Import(RestDocsConfig.class)
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class CommonControllerTest {

    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    public TokenUtil tokenUtil;

    @MockBean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @MockBean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @MockBean
    public AdminGetService adminGetService;

    @BeforeEach
    public void setUp(final WebApplicationContext context, final RestDocumentationContextProvider provider) throws Exception {

        Admin admin = Admin.builder()
                .adminId(1L)
                .loginId("test1234")
                .password("password")
                .role(Role.ADMIN)
                .approvedStatus(ApprovalStatus.APPROVAL)
                .build();

        // user.getSocialId() 에서 NullPointerException 방지를 위한 Authentication 생성
        AuthenticationUtil.makeAuthentication(admin);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))  // rest docs 설정 주입
                .alwaysDo(MockMvcResultHandlers.print()) // andDo(print()) 코드 포함
                .alwaysDo(restDocs) // pretty 패턴과 문서 디렉토리 명 정해준것 적용
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글 깨짐 방지
                .build();
    }
}

