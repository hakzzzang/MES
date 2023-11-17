package com.andong.smartfactoryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.andong.smartfactoryweb"})
public class SmartfactoryWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartfactoryWebApplication.class, args);
	}

}
