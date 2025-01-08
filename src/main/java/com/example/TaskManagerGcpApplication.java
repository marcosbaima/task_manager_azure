package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.function.Function;

@SpringBootApplication
public class TaskManagerGcpApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerGcpApplication.class, args);
    }

    @Bean
    public Function<String, String> processTask() {
        return task -> "Task processed: " + task;
    }
}
