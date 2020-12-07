package fr.training.spring.shop.web.dto;

import java.io.Serializable;
import java.util.Set;


public class OrderLightDTO implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * customerID of type String
	 */
	private String customerId;

	/**
	 * items of type Set of ItemDTO
	 */
	private Set<String> itemIds;

	/**
	 *
	 */
	public OrderLightDTO() {
	}

	public OrderLightDTO(final String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	public Set<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(final Set<String> itemIds) {
		this.itemIds = itemIds;
	}

}
