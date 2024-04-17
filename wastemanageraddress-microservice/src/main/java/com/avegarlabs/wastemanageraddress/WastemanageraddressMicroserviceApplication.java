package com.avegarlabs.wastemanageraddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WastemanageraddressMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WastemanageraddressMicroserviceApplication.class, args);
	}

}
