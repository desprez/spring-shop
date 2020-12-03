
package fr.training.samples.spring.shop.application.item;

import java.util.List;

import fr.training.samples.spring.shop.domain.item.Item;

public interface ItemService {

	/**
	 * Add Item to the catalog
	 *
	 * @param item the item to add
	 * @return the added item
	 */
	Item addItem(Item item);

	/**
	 * Get the all item list.
	 *
	 * @return a list of item entities
	 */
	List<Item> getAllItems(Item item);

}