package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.List;

public class OrderLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;

	private List<String> itemIds;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public OrderLightDto() {
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	public List<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(final List<String> itemIds) {
		this.itemIds = itemIds;
	}

}
