package ch.kchmiel.airpollution;

import ch.kchmiel.airpollution.service.AirPollutionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirPollutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirPollutionApplication.class, args);
	}

}
