package ro.uaic.fii.SessionAggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SessionAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionAggregatorApplication.class, args);
	}

}
