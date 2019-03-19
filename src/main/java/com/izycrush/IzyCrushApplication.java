package com.izycrush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@SpringBootApplication
public class IzyCrushApplication
{

	public static void main(String[] args) {
		SpringApplication.run(IzyCrushApplication.class, args);
	}
}
