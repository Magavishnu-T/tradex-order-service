package com.tradex.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TradexOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradexOrderServiceApplication.class, args);
	}

}
