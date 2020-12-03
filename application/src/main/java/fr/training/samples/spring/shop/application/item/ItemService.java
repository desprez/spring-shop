
package fr.training.samples.spring.shop.application.item;

import java.util.List;

import fr.training.samples.spring.shop.domain.item.Item;

public interface ItemService {

	/**
	 * Add Item to the catalog
	 * @param item
	 * @return
	 */
	Item addItem(Item item);

	/**
	 * @return a list of item entities
	 */
	List<Item> getAllItems(Item item);

}