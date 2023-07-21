package com.cs.investmentAdvise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestmentAdviseApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentAdviseApplication.class, args);
	}

}
