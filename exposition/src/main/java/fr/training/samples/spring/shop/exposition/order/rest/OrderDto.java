package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.training.samples.spring.shop.exposition.item.rest.ItemDto;

public class OrderDto implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * orderID of type String
	 */
	private String orderID;

	/**
	 * customerID of type String
	 */
	private String customerID;

	/**
	 * items of type Set of ItemDTO
	 */
	private Set<ItemDto> items;

	/**
	 *
	 */
	public OrderDto() {
	}

	/**
	 * @param orderID
	 * @param customerID
	 */
	public OrderDto(final String orderID, final String customerID) {
		this.orderID = orderID;
		this.customerID = customerID;
		items = new HashSet<>();
	}

	/**
	 * @param itemDTO
	 */
	public void addItem(final ItemDto itemDto) {
		items.add(itemDto);
	}

	/**
	 * @return
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return
	 */
	public Set<ItemDto> getItems() {
		return items;
	}

	/**
	 * @return
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * @param customerID
	 */
	public void setCustomerID(final String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @param items
	 */
	public void setItems(final Set<ItemDto> items) {
		this.items = items;
	}

	/**
	 * @param orderID
	 */
	public void setOrderID(final String orderID) {
		this.orderID = orderID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OrderDTO [orderID=");
		builder.append(orderID);
		builder.append(", customerID=");
		builder.append(customerID);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

}
