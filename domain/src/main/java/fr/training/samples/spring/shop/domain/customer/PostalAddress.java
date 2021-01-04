package fr.training.samples.spring.shop.domain.customer;

import java.io.Serializable;
import java.util.Objects;

/**
 * Postal adress (Value Object).
 */
public class PostalAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	private String street;
	private String city;
	private String country;
	private String postalCode;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	PostalAddress() {

	}

	public PostalAddress(final String street, final String city, final String country, final String postalCode) {
		this.street = street;
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	@Override
	public boolean equals(final Object arg) {
		if (!(arg instanceof PostalAddress)) {
			return false;
		}
		final PostalAddress other = (PostalAddress) arg;
		return Objects.equals(street, other.street) //
				&& Objects.equals(city, other.city) //
				&& Objects.equals(country, other.country) //
				&& Objects.equals(postalCode, other.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(street, city, country, postalCode);
	}

}