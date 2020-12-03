package fr.training.samples.spring.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" })
public class SpringBootApp {

	/**
	 * Main method
	 * @param args main aguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

}
