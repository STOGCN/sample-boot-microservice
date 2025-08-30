package lab.microservice.hello;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {


	// basic hello 
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

	@Autowired
    private UserServiceProxy proxy;

	@GetMapping("/api/greet/{id}")
    public ResponseEntity<String> greet(@PathVariable Long id) {

        // call user-service via Feign
        User user = proxy.retrieveUsername(id);

        LocalTime currentTime = LocalTime.now();
        String greeting = "Hello!";
        if (currentTime.isBefore(LocalTime.NOON)) {
            greeting = "Good morning!";
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            greeting = "Good afternoon!";
        } else {
            greeting = "Good evening!";
        }
        
        String message = greeting + " " + user.getName() + "! (from port " + user.getPort() + ")";
        System.out.println("RESPONSE: " + message);

        return ResponseEntity.ok(message);
    }

}