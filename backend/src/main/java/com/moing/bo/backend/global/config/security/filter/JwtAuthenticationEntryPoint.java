package com.moing.bo.backend.global.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moing.bo.backend.global.config.security.jwt.JwtExceptionList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 인증되지 않은 사용자가 보호된 리소스에 액세스 할 때 호출
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = String.valueOf(request.getAttribute("exception"));
        log.info("commence:" + exception);

        switch (exception) {
            case "J0007":
                setResponse(response, JwtExceptionList.ADDITIONAL_REQUIRED_TOKEN);
                break;

            case "J0001":
                setResponse(response, JwtExceptionList.UNKNOWN_ERROR);
                break;

            case "J0002":
                setResponse(response, JwtExceptionList.MAL_FORMED_TOKEN);
                break;

            case "J0006":
                setResponse(response, JwtExceptionList.ILLEGAL_TOKEN);
                break;

            case "J0003":
                setResponse(response, JwtExceptionList.EXPIRED_TOKEN);
                break;

            case "J0004":
                setResponse(response, JwtExceptionList.UNSUPPORTED_TOKEN);
                break;

            default:
                setResponse(response, JwtExceptionList.ACCESS_DENIED);
                break;
        }

    }

    private void setResponse(HttpServletResponse response, JwtExceptionList exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("isSuccess", false);
        responseJson.put("timestamp", LocalDateTime.now().withNano(0).toString());
        responseJson.put("errorCode", exceptionCode.getErrorCode());
        responseJson.put("message", exceptionCode.getMessage());

        response.getWriter().print(objectMapper.writeValueAsString(responseJson));
    }
}