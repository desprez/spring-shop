package fr.training.samples.spring.shop.exposition.customer.rest;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("password")
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

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@ApiModelProperty(example = "password", required = true, value = "Customer password")
	@NotNull
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
