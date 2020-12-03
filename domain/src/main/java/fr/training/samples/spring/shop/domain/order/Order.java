package fr.training.samples.spring.shop.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;

@Entity
public class Order extends AbstractBaseEntity {

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "ITEM_ORDERS", joinColumns = { @JoinColumn(name = "ORDERS_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ITEMS_ID") })
	private final List<Item> items = new ArrayList<>();

	private Integer total;

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
