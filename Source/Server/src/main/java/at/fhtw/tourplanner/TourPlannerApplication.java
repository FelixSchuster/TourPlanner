package at.fhtw.tourplanner;

import at.fhtw.tourplanner.utils.ApplicationFileParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TourPlannerApplication {
	//System.out.println("API KEY: " + API_KEY);
	public static final String API_KEY = ApplicationFileParser.parseApiKey();
	public static void main(String[] args) {
		System.out.println("API KEY: " + API_KEY);

		SpringApplication.run(TourPlannerApplication.class, args);
	}
}
