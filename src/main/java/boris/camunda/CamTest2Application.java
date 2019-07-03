package boris.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(CamTest2Application.class, args);
	}

}
