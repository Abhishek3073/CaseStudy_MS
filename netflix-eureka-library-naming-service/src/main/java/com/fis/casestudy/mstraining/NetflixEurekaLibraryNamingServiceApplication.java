package com.fis.casestudy.mstraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaLibraryNamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaLibraryNamingServiceApplication.class, args);
	}

}
