package fr.training.samples.spring.shop.domain.customer;

import org.apache.commons.lang3.Validate;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

public class Customer extends AbstractBaseEntity {

	private String name;

	private String password;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	Customer() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Customer [name=").append(name).append(", password=").append(password).append("]");
		return builder.toString();
	}

	/**
	 * private constructor to enforce Builder usage
	 */
	private Customer(final Builder builder) {
		if (builder.id != null) {
			id = builder.id;
		}
		name = builder.name;
		password = builder.password;
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern to ensure Customer is immutable.
	 */
	public static class Builder {
		private String id;
		private String name;
		private String password;

		public Builder id(final String id) {
			this.id = id;
			return this;
		}

		public Builder name(final String name) {
			this.name = name;
			return this;
		}

		public Builder password(final String password) {
			this.password = password;
			return this;
		}

		public Customer build() {
			Validate.notNull(name, "customer's name is required");
			Validate.notNull(password, "customer's password is required");
			return new Customer(this);
		}

	}

}
