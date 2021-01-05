package fr.training.samples.spring.shop.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PostalAddressTest {

	@Test
	public void two_same_objects_should_have_same_hashCode() {
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456").hashCode())
		.isEqualTo(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456").hashCode());
	}

	@Test
	public void two_same_objects_should_be_equals() {
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456"))
		.isEqualTo(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456"));
	}

	@Test
	public void two_different_objects_shouldnt_be_equals() {
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456"))
		.isNotEqualTo(new PostalAddress("110 main street", "Las Vegas", "Eldorado", "123456"));
	}

	@Test
	public void toString_should_display_content() {
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456")).asString()
		.contains("10 main street");
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456")).asString()
		.contains("Las Vegas");
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456")).asString()
		.contains("Eldorado");
		assertThat(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456")).asString()
		.contains("123456");

	}

}
