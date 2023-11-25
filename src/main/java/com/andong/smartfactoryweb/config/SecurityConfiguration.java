package com.andong.smartfactoryweb.config;

import com.andong.smartfactoryweb.app.user.service.UserServiceImpl;
import com.andong.smartfactoryweb.common.exception.AjaxAwareAuthenticationEntryPoint;
import com.andong.smartfactoryweb.config.handler.LoginFailureHandler;
import com.andong.smartfactoryweb.config.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * @FileName : SecurityConfiguration.java
 * @Date : 2021. 3. 2.
 * @author : dooho
 * @Class 설명 : Spring Security 설정 클래스
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserServiceImpl userService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public AuthenticationFailureHandler loginFailureHandler() { return new LoginFailureHandler(); }

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() { return new LoginSuccessHandler(); }

	@Bean
	public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
		return new AjaxAwareAuthenticationEntryPoint("/login");
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
	
	@Override
    protected void configure(HttpSecurity security) throws Exception{
		security.csrf().disable().authorizeRequests()
					.antMatchers("/login", "/privacy", "/sample/**", "/rest/**", "/file/**").permitAll()
					.anyRequest().authenticated()
				.and()
					.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.failureHandler(loginFailureHandler())
						.successHandler(loginSuccessHandler())
				.and()
					.logout()
						.logoutSuccessUrl("/login")
						.invalidateHttpSession(true)
				.and()
					.exceptionHandling()
						.authenticationEntryPoint(loginUrlAuthenticationEntryPoint());

    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
   }

}
