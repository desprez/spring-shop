package fr.training.samples.spring.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" })
public class SpringBootApp extends SpringBootServletInitializer {

	/**
	 * @param args main aguments
	 */
	public static void main(final String[] args) {

		SpringApplication.run(SpringBootApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
		return builder.sources(SpringBootApp.class);
	}

}
