package fr.training.samples.spring.shop.domain.order;

import org.apache.commons.lang3.Validate;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.item.Item;

public class OrderItem extends AbstractBaseEntity {

	private Item item;

	private Integer quantity;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	OrderItem() {
	}

	public OrderItem(final Item item, final Integer quantity) {
		Validate.notNull(item, "item is required");
		Validate.isTrue(quantity > 0, "quantity must be greater than zero");
		this.item = item;
		this.quantity = quantity;
	}

	public OrderItem(final Item item) {
		this(item, 1);
	}

	public Item getItem() {
		return item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("OrderItem [item=").append(item) //
		.append(", quantity=").append(quantity) //
		.append(", id=").append(id) //
		.append("]");
		return builder.toString();
	}

}
