package fr.training.samples.spring.shop.domain.customer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * Password (Value Object).
 */
public class Password implements Serializable {

	private static final long serialVersionUID = 1L;

	private char[] value;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	Password() {

	}

	public Password(final String value) {
		Objects.requireNonNull(value, "value is required");
		this.value = value.toCharArray();
	}

	public static Password of(final String value) {
		return new Password(value);
	}

	public String getValue() {
		return String.valueOf(value);
	}

	public String getObfuscatedValue() {
		return StringUtils.repeat("*", value.length);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Password) {
			return Arrays.equals(value, ((Password) obj).value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(value);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [value=" + value.hashCode() + "]";
	}

}