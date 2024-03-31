package net.javaproject.TaskPlanner;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskPlannerApplication {
    @Bean
	public ModelMapper modelmapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApplication.class, args);
	}

}
