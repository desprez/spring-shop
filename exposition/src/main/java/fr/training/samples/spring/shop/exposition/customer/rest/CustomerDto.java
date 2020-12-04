package fr.training.samples.spring.shop.exposition.customer.rest;

import java.io.Serializable;

public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String password;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public CustomerDto() {

	}

	/**
	 * @param name
	 * @param password
	 */
	public CustomerDto(final String name, final String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * @param customerID
	 * @param name
	 * @param password
	 */
	public CustomerDto(final String id, final String name, final String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

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

}
