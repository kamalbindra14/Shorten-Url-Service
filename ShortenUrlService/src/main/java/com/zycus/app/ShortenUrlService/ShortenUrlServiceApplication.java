package com.zycus.app.ShortenUrlService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zycus.app")
public class ShortenUrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortenUrlServiceApplication.class, args);
	}
}
