package fr.training.samples.spring.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" }, lazyInit = true)
public class SpringBootAppTest {
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(SpringBootAppTest.class, args);
	}
}
