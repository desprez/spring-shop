package fr.training.samples.spring.shop.exposition.customer.rest;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer", description = "Customer informations")
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

	@ApiModelProperty(example = "John Doe", required = true, value = "Customer name")
	@NotNull
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
