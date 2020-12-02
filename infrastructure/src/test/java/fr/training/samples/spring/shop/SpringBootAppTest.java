package fr.training.samples.spring.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Badr NASS
 *
 */
@SpringBootConfiguration
@ComponentScan(basePackages = { "fr.training.samples.spring.shop" }, lazyInit = true)
@EntityScan(basePackages = { "fr.training.samples.spring.shop.domain" })
@EnableJpaRepositories
public class SpringBootAppTest {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        SpringApplication.run(SpringBootAppTest.class, args);
    }
}
