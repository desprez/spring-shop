package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.Set;

public class OrderLightDto implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * customerID of type String
	 */
	private String customerID;

	/**
	 * items of type Set of ItemDTO
	 */
	private Set<String> items;

	/**
	 *
	 */
	public OrderLightDto() {
	}

	/**
	 * @return
	 */
	public String getCustomerID() {
		return customerID;
	}

	public Set<String> getItems() {
		return items;
	}

	public void setItems(final Set<String> items) {
		this.items = items;
	}

	public void setCustomerID(final String customerID) {
		this.customerID = customerID;
	}

}
