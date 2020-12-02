package fr.training.samples.spring.shop.exposition.item.rest;

import java.io.Serializable;

public class ItemLightDto implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * description of type String
	 */
	private String description;

	/**
	 * price of type int
	 */
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

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param price price
	 */
	public void setPrice(final int price) {
		this.price = price;
	}

}
