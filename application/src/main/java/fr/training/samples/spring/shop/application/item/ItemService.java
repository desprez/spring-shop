
package fr.training.samples.spring.shop.application.item;

import java.util.List;

import fr.training.samples.spring.shop.domain.item.Item;

public interface ItemService {

	/**
	 * Add Item to the catalog
	 *
	 * @param item
	 * @return
	 */
	public Item addItem(Item item);

	/**
	 * Display items catalog
	 *
	 * @return a list of item entities
	 */
	public List<Item> getAllItems();

}