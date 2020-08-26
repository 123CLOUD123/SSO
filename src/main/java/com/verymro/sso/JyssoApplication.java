package com.verymro.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.verymro")
@MapperScan(value={"com.verymro.sso.mapper"})
public class JyssoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JyssoApplication.class, args);
	}

}
