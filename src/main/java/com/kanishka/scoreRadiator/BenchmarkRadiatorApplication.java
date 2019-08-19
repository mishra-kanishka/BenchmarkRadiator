package com.kanishka.scoreRadiator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BenchmarkRadiatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenchmarkRadiatorApplication.class, args);
	}

}
