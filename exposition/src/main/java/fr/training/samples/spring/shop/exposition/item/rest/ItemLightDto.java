package fr.training.samples.spring.shop.exposition.item.rest;

import java.io.Serializable;

public class ItemLightDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;

	private int price;

	/**
	 *
	 */
	public ItemLightDto() {
	}

	public ItemLightDto(final String description, final int price) {
		super();
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

}
