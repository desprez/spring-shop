package fr.training.samples.spring.shop.application.item;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	public ItemServiceImpl(final ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public Item addItem(final Item item) {
		itemRepository.save(item);
		return item;
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

}
