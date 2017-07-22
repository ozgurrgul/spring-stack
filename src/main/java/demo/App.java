package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
        RepositoryRestMvcAutoConfiguration.class
})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
