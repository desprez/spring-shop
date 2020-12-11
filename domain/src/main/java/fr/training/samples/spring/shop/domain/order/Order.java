package fr.training.samples.spring.shop.domain.order;

import java.util.ArrayList;
import java.util.List;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;

public class Order extends AbstractBaseEntity {

	private Customer customer;

	private List<Item> items = new ArrayList<>();

	private Integer total = 0;

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(final List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(final Integer total) {
		this.total = total;
	}

	public void addItem(final Item item) {
		items.add(item);
	}

}
