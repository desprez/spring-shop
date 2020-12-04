package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.training.samples.spring.shop.exposition.item.rest.ItemDto;

public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String customerId;

	private Set<ItemDto> items;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public OrderDto() {

	}

	public OrderDto(final String orderId, final String customerId) {
		this.orderId = orderId;
		this.customerId = customerId;
		items = new HashSet<>();
	}

	public void addItem(final ItemDto itemDto) {
		items.add(itemDto);
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(final String orderId) {
		this.orderId = orderId;
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
	 * @return the items
	 */
	public Set<ItemDto> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(final Set<ItemDto> items) {
		this.items = items;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OrderDTO [orderId=");
		builder.append(orderId);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

}
