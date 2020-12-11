package fr.training.samples.spring.shop.domain.customer;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

public class Customer extends AbstractBaseEntity {

	private String name;

	private String password;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Customer [name=").append(name).append(", password=").append(password).append("]");
		return builder.toString();
	}

}
