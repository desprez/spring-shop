package fr.training.samples.spring.shop.exposition.customer.rest;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer", description = "Customer informations")
@Validated
public class CustomerLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String password;

	private String email;

	private String street;

	private String city;

	private String country;

	private String postalCode;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public CustomerLightDto() {

	}

	/**
	 * @param name the customer name
	 * @param password the customer password
	 */
	public CustomerLightDto(final String name, final String password) {
		super();
		this.name = name;
		this.password = password;
	}

	@ApiModelProperty(example = "John Doe", required = true, value = "Customer name")
	@Size(min = 5)
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@ApiModelProperty(example = "password", required = true, value = "Customer password")
	@Size(min = 5)
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@ApiModelProperty(example = "email", required = true, value = "Customer email")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@ApiModelProperty(example = "10 main street", required = true, value = "Customer street")
	public String getStreet() {
		return street;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	@ApiModelProperty(example = "Las vegas", required = true, value = "Customer city")
	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@ApiModelProperty(example = "Eldorado", required = true, value = "Customer country")
	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	@ApiModelProperty(example = "12345", required = true, value = "Customer postal code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("CustomerLightDto [name=").append(name) //
		.append(", password=").append(password) //
		.append(", email=").append(email) //
		.append(", street=").append(street) //
		.append(", city=").append(city) //
		.append(", country=").append(country) //
		.append(", postalCode=").append(postalCode) //
		.append("]");
		return builder.toString();
	}

}
