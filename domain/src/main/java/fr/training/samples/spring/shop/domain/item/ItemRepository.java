package fr.training.samples.spring.shop.domain.item;

import java.util.List;

/**
 * Repository for Item entity
 */
public interface ItemRepository {

	public Item findById(String itemId);

	public void save(Item item);

	public List<Item> findAll();

	/**
	 * Find items according to an item identifier list
	 *
	 * @param ids identifier list
	 * @return a list of items
	 */
	public List<Item> findById(List<String> ids);

}
