package com.example.Task.Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:env.properties")
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
