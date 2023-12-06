package com.andong.smartfactoryweb.config.handler;

import com.andong.smartfactoryweb.app.user.service.UserService;
import com.andong.smartfactoryweb.app.user.vo.LoginVO;
import com.andong.smartfactoryweb.app.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Autowired
    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        try {
            log.debug("onAuthenticationSuccess");
            LoginVO userDetails = (LoginVO) authentication.getPrincipal();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        String userId = request.getParameter("username");
        String password = request.getParameter("password");

        // DB에서 사용자 정보 가져오기
        UserVO user = userService.findUserByUserId(userId);

        if (user != null) {
            // 비밀번호 일치: 로그인 성공
            log.info("로그인 성공: {}", userId);

            // 세션에 사용자 정보 추가
            request.getSession().setAttribute("user", user);
            log.info("세션에 저장된 user: {}", request.getSession().getAttribute("user"));

            response.sendRedirect("/SF/main");
        }
    }
}