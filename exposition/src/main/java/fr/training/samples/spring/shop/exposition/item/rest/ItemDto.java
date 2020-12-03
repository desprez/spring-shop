package fr.training.samples.spring.shop.exposition.item.rest;

import java.io.Serializable;

public class ItemDto implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * itemId of type String
	 */
	private String itemId;

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
	public ItemDto() {
	}

	/**
	 * @param description description
	 * @param price       price
	 */
	public ItemDto(final String description, final int price) {
		this.description = description;
		this.price = price;
	}

	/**
	 * @param itemId      itemId
	 * @param description description
	 * @param price       price
	 */
	public ItemDto(final String itemId, final String description, final int price) {
		this.itemId = itemId;
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
	public String getItemId() {
		return itemId;
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
	 * @param itemId itemId
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @param price price
	 */
	public void setPrice(final int price) {
		this.price = price;
	}

}
