package org.vivek.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CodingAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingAnalyticsApplication.class, args);
	}

}
