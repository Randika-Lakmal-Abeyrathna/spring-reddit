package com.randikalakmal.springreddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringRedditApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedditApplication.class, args);
	}

}
