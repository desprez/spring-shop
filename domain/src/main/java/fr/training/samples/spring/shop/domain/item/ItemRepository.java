package fr.training.samples.spring.shop.domain.item;

public interface ItemRepository {

	Item findById(String itemId);

	void save(Item item);

}
