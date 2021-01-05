package fr.training.samples.spring.shop.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EmailAdressTest {

	@Test
	public void getValue_should_return_value() {
		assertThat(EmailAdress.of("michel.dupont@gmail.com").getValue()).isEqualTo("michel.dupont@gmail.com");
	}

	@Test
	public void two_same_objects_should_have_same_hashCode() {
		assertThat(EmailAdress.of("michel.dupont@gmail.com").hashCode())
		.isEqualTo(EmailAdress.of("michel.dupont@gmail.com").hashCode());
	}

	@Test
	public void two_same_objects_should_be_equals() {
		assertThat(EmailAdress.of("michel.dupont@gmail.com")).isEqualTo(EmailAdress.of("michel.dupont@gmail.com"));
	}

	@Test
	public void two_different_objects_shouldnt_be_equals() {
		assertThat(EmailAdress.of("michel.dupont@gmail.com")).isNotEqualTo(EmailAdress.of("michel.dupond@gmail.com"));
	}

	@Test
	public void toString_should_display_content() {
		assertThat(EmailAdress.of("michel.dupont@gmail.com")).asString().contains("michel.dupont@gmail.com");
	}

}
