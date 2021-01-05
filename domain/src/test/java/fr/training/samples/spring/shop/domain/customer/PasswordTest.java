package fr.training.samples.spring.shop.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PasswordTest {

	@Test
	public void getValue_should_return_password_in_clear() {
		assertThat(Password.of("password").getValue()).isEqualTo("password");
	}

	@Test
	public void two_same_objects_should_have_same_hashCode() {
		assertThat(Password.of("password").hashCode()).isEqualTo(Password.of("password").hashCode());
	}

	@Test
	public void two_same_objects_should_have_same_value() {
		assertThat(Password.of("password")).isEqualTo(Password.of("password"));
	}

	@Test
	public void two_different_objects_shouldnt_have_same_value() {
		assertThat(Password.of("password")).isNotEqualTo(Password.of("drowssap"));
	}

	@Test
	public void toString_dont_show_password_value() {
		assertThat(Password.of("passwordinclear").toString()).isNotEqualTo("passwordinclear");
	}

}
