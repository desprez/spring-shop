package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Order", description = "Order informations")
public class OrderLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;

	private List<String> itemIds;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public OrderLightDto() {
	}

	@ApiModelProperty(required = true, value = "customerId")
	@NotNull
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	@ApiModelProperty(required = true, value = "itemIds")
	@NotNull
	public List<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(final List<String> itemIds) {
		this.itemIds = itemIds;
	}

}
