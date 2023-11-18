package com.andong.smartfactoryweb.config.handler;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	private final String DEFAULT_FAILURE_URL = "/login?error=true";


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String errorCode = null;

//		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
//			errorCode = ApiResponseCode.BAD_CREDENTIALS.getCode();
//		} else if(exception instanceof DisabledException) {
//			errorCode = ApiResponseCode.USER_DISABLED.getCode();
//		} else if(exception instanceof CredentialsExpiredException) {
//			errorCode = ApiResponseCode.CREDENTIAL_EXPIRED.getCode();
//		} else {
//			errorCode = ApiResponseCode.LOGIN_ERROR.getCode();
//		}

		response.sendRedirect(DEFAULT_FAILURE_URL + "&errorCode=" + errorCode);
	}
}