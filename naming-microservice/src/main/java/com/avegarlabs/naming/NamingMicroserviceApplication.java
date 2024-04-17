package com.avegarlabs.naming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingMicroserviceApplication.class, args);
	}

}
