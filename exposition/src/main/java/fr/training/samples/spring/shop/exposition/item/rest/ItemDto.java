package fr.training.samples.spring.shop.exposition.item.rest;

import java.io.Serializable;

public class ItemDto implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * itemID of type String
	 */
	private String itemID;

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
	 * @param itemID      itemID
	 * @param description description
	 * @param price       price
	 */
	public ItemDto(final String itemID, final String description, final int price) {
		this.itemID = itemID;
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
	public String getItemID() {
		return itemID;
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
	 * @param itemID itemID
	 */
	public void setItemID(final String itemID) {
		this.itemID = itemID;
	}

	/**
	 * @param price price
	 */
	public void setPrice(final int price) {
		this.price = price;
	}

}
