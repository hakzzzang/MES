package com.andong.smartfactoryweb.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @FileName : ServletConfig.java
 * @Date : 2021. 5. 18.
 * @author : dooho
 * @Class 설명 : Sevlet 설정을 위한 클래스
 */

@EnableWebMvc
@Configuration
public class ServletConfiguration implements WebMvcConfigurer {

	ServletConfiguration() {
		super();
	}
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(),
						HttpMethod.PUT.name());
    }
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
		
	}
}
