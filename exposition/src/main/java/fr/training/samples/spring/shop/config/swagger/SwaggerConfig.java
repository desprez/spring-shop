package fr.training.samples.spring.shop.config.swagger;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket customImplementation() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("fr.training.samples.spring.shop.exposition")).build()
				.directModelSubstitute(LocalDate.class, java.sql.Date.class)//
				.directModelSubstitute(ZonedDateTime.class, java.util.Date.class) //
				.apiInfo(apiInfo());
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("Swagger spring-shop") //
				.description("No description provided") //
				.license("Apache 2.0") //
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html") //
				.termsOfServiceUrl("") //
				.version("1.0") //
				.contact(new Contact("Some one", "http://localhost:8080", "SomeOne@training.org")) //
				.build();
	}
}