package com.moing.bo.backend.global.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moing.bo.backend.global.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// @Component 어노테이션은 이 클래스가 Spring의 빈으로 관리되어야 함을 나타냅니다.
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    // handle 메서드는 접근이 거부될 때 호출됩니다.
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // ErrorResponse는 사용자 정의 응답 클래스일 것입니다. 이 클래스로부터 객체를 생성하고, 오류 메시지를 설정합니다.
        ErrorResponse errorResponse = new ErrorResponse("403", "접근이 금지되었습니다. 권한이 없는 사용자가 접근하려고 했습니다.");

        // 응답 상태를 FORBIDDEN (403)으로 설정합니다.
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // 응답의 컨텐트 타입을 JSON으로 설정합니다.
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // ObjectMapper를 사용하여 ErrorResponse 객체를 JSON으로 변환하고, 응답에 씁니다.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), errorResponse);
    }
}
