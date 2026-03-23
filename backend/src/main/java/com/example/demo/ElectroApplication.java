package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElectroApplication {

	public static void main(String[] args) {
		System.out.println("JVM Timezone = " + java.util.TimeZone.getDefault());
		SpringApplication.run(ElectroApplication.class, args);
	}

}
