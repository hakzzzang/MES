package com.andong.smartfactoryweb.config.handler;


import com.andong.smartfactoryweb.app.user.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            log.debug("onAuthenticationSuccess");
            LoginVO userDetails = (LoginVO) authentication.getPrincipal();
        } catch(Exception e) {
            log.error(e.getMessage());
        }

        response.sendRedirect("/sample/main");
    }
}