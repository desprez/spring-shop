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

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the itemIds
	 */
	public List<String> getItemIds() {
		return itemIds;
	}

	/**
	 * @param itemIds the itemIds to set
	 */
	public void setItemIds(final List<String> itemIds) {
		this.itemIds = itemIds;
	}

}
