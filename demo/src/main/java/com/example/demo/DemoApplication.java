package com.example.demo;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(UserRepo repo) {
		return args -> {
			User user1=new User("John","Doe");
			repo.save(user1);
			User user2=new User("John","Doe");
			repo.save(user2);
			User user3=new User("John","Doe");
			repo.save(user3);
		};
	}

}
