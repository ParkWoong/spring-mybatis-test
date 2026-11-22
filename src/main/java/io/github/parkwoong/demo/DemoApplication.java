package io.github.parkwoong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
@SpringBootApplication
public class DemoApplication {
 public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }
 @Bean CommandLineRunner demo(RequestRepository repository) { return args -> { repository.save(new RequestEntry("sample", "GET", 200)); System.out.println(repository.findAll()); }; }
}
