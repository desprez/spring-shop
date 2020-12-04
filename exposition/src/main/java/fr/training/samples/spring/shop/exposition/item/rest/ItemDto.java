package fr.training.samples.spring.shop.exposition.item.rest;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("itemId")
	private String itemId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("price")
	private int price;

	/**
	 * No-arg constructor for JavaBean tools
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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

}
