package fr.training.samples.spring.shop.exposition.customer.rest;

import java.io.Serializable;

public class CustomerLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String password;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public CustomerLightDto() {

	}

	/**
	 * @param name
	 * @param password
	 */
	public CustomerLightDto(final String name, final String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

}
