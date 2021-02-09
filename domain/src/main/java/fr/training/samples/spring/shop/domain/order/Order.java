package fr.training.samples.spring.shop.domain.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.Customer;

public class Order extends AbstractBaseEntity {

	private Customer customer;

	private final List<OrderItem> orderItems = new ArrayList<>();

	private Integer total;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	Order() {

	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the items
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	public void addOrderItem(final OrderItem orderItem) {
		orderItems.add(orderItem);
		total = Integer.sum(total, orderItem.getItem().getPrice() * orderItem.getQuantity());
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Order [customer=").append(customer) //
		.append(", orderItems=").append(orderItems) //
		.append(", total=").append(total) //
		.append("]");
		return builder.toString();
	}

	/**
	 * private constructor to enforce Builder usage
	 *
	 * @param builder the Order.builder
	 */
	private Order(final Builder builder) {
		if (builder.id != null) {
			id = builder.id;
		}
		customer = builder.customer;
		total = 0;
		for (final OrderItem item : builder.orderItems) {
			addOrderItem(item);
		}
	}

	/**
	 * Builder static assessor
	 *
	 * @return the Order.builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern to ensure Order is immutable.
	 */
	public static class Builder {
		private String id;
		private Customer customer;
		private List<OrderItem> orderItems = new ArrayList<>();

		public Builder id(final String id) {
			this.id = id;
			return this;
		}

		public Builder customer(final Customer customer) {
			this.customer = customer;
			return this;
		}

		public Builder addOrderItem(final OrderItem orderItem) {
			orderItems.add(orderItem);
			return this;
		}

		public Builder orderItems(final List<OrderItem> orderItems) {
			this.orderItems = orderItems;
			return this;
		}

		public Order build() {
			Validate.notNull(customer, "Customer is required");
			Validate.isTrue(!orderItems.isEmpty(), "Order must have one item at least");
			return new Order(this);
		}

	}

}
