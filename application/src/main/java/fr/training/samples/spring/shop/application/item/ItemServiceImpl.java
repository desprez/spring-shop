package fr.training.samples.spring.shop.application.item;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

/**
 * These class methods implements Item's Use cases.
 */
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	/**
	 * Constructor for Bean injection
	 *
	 * @param itemRepository the ItemRepository bean
	 */
	public ItemServiceImpl(final ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.item.ItemService#addItem(fr.
	 * training.samples.spring.shop.domain.item.Item)
	 */
	@Transactional
	@Override
	public Item addItem(final Item item) {
		itemRepository.save(item);
		return item;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.item.ItemService#getAllItems()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

}
