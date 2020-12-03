package fr.training.samples.spring.shop.domain.item;

import java.util.List;

public interface ItemRepository {

	public Item findById(String itemId);

	public void save(Item item);

	public List<Item> findAll();

	public List<Item> findById(List<String> ids);

}
