package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class
		//, ErrorMvcAutoConfiguration.class
})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
