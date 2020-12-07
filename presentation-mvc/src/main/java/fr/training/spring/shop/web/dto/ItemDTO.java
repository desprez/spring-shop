package fr.training.spring.shop.web.dto;

import java.io.Serializable;


public class ItemDTO implements Serializable {

	/**
	 * serialVersionUID of type long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * itemID of type String
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
	public ItemDTO() {
	}

	/**
	 * @param description description
	 * @param price price
	 */
	public ItemDTO(final String description, final int price) {
		this.description = description;
		this.price = price;
	}

	/**
	 * @param itemId itemId
	 * @param description description
	 * @param price price
	 */
	public ItemDTO(final String itemId, final String description, final int price) {
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
	 * @param itemID itemID
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
