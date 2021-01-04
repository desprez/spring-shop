package fr.training.samples.spring.shop.domain.item;

import org.apache.commons.lang3.Validate;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

public class Item extends AbstractBaseEntity {

	private String description;

	private Integer price;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	Item() {
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(final Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Item [description=").append(description).append(", price=").append(price).append("]");
		return builder.toString();
	}

	/**
	 * private constructor to enforce Builder usage
	 */
	private Item(final Builder builder) {
		if (builder.id != null) {
			id = builder.id;
		}
		description = builder.description;
		price = builder.price;
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern to ensure Item is immutable.
	 */
	public static class Builder {
		private String id;
		private String description;
		private Integer price;

		public Builder id(final String id) {
			this.id = id;
			return this;
		}

		public Builder description(final String description) {
			this.description = description;
			return this;
		}

		public Builder price(final Integer price) {
			this.price = price;
			return this;
		}

		public Item build() {
			Validate.notNull(description, "Item description is required");
			Validate.notNull(price, "Item price is required");
			return new Item(this);
		}

	}

}
