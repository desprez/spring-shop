package fr.training.samples.spring.shop.domain.customer;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;

/**
 * Email adress (Value Object).
 */
public class EmailAdress implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	EmailAdress() {

	}

	public EmailAdress(@Email final String value) {
		this.value = Objects.requireNonNull(value, "value is required");
	}

	public static EmailAdress of(final String value) {
		return new EmailAdress(value);
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof EmailAdress) {
			return value.equals(((EmailAdress) obj).value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [value=" + value + "]";
	}

}