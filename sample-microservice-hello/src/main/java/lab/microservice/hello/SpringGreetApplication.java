package lab.microservice.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringGreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGreetApplication.class, args);
	}

}
