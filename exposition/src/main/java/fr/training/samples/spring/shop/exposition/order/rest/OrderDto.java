package fr.training.samples.spring.shop.exposition.order.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.training.samples.spring.shop.exposition.customer.rest.CustomerDto;
import fr.training.samples.spring.shop.exposition.item.rest.ItemDto;

public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private CustomerDto customer;

	private List<ItemDto> items;

	private int total;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public OrderDto() {

	}

	public OrderDto(final String id, final CustomerDto customer ) {
		this.id = id;
		this.customer = customer;
		items = new ArrayList<>();
	}

	public void addItem(final ItemDto itemDto) {
		items.add(itemDto);
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(final CustomerDto customer) {
		this.customer = customer;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(final List<ItemDto> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(final int total) {
		this.total = total;
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
		builder.append(id);
		builder.append(", customerId=");
		builder.append(customer);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

}
