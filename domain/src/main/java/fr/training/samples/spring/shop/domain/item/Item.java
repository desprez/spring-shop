package fr.training.samples.spring.shop.domain.item;

import javax.persistence.Entity;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

@Entity
public class Item extends AbstractBaseEntity {

	private String description;

	private Integer price;

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

}
