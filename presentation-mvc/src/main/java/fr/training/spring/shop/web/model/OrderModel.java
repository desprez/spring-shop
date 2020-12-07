package fr.training.spring.shop.web.model;

import java.util.List;
import java.util.Set;

import fr.training.spring.shop.web.dto.ItemDTO;

public class OrderModel {

	private String customerId;

	private Set<String> itemIds;

	private List<ItemDTO> items;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(final List<ItemDTO> items) {
		this.items = items;
	}

	public Set<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(final Set<String> itemIds) {
		this.itemIds = itemIds;
	}

}
