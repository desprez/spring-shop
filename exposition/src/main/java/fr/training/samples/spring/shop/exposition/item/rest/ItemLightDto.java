package fr.training.samples.spring.shop.exposition.item.rest;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Item", description = "Item informations")
@Validated
public class ItemLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("description")
	private String description;

	@JsonProperty("price")
	private int price;

	public ItemLightDto() {
	}

	public ItemLightDto(final String description, final int price) {
		super();
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@ApiModelProperty(example = "10", required = true, value = "Price")
	@NotNull
	@Positive
	public int getPrice() {
		return price;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ItemLightDto [description=").append(description).append(", price=").append(price).append("]");
		return builder.toString();
	}

}
