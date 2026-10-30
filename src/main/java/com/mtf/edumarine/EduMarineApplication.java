package com.mtf.edumarine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.security.Security;

@EnableScheduling
@SpringBootApplication
public class EduMarineApplication {

	public static void main(String[] args) {

		disableAddressCache();

		SpringApplication.run(EduMarineApplication.class, args);
	}

	private static void disableAddressCache() {
		Security.setProperty("networkaddress.cache.ttl", "0");
		Security.setProperty("networkaddress.cache.negative.ttl", "0");
	}

}
