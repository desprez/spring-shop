package fr.training.samples.spring.shop.infrastructure.item;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

/**
 * Item Repository implementation using JPA
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private final ItemJpaRepository itemJpaRepository;

	/**
	 * Constructor for Bean injection
	 */
	public ItemRepositoryImpl(final ItemJpaRepository itemJpaRepository) {
		this.itemJpaRepository = itemJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.item.ItemRepository#findById(java.lang
	 * .String)
	 */
	@Override
	public Item findById(final String itemId) {
		return itemJpaRepository.findById(itemId).orElseThrow(() -> new NotFoundException());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.item.ItemRepository#findById(java.util
	 * .List)
	 */
	@Override
	public List<Item> findById(final List<String> ids) {
		return itemJpaRepository.findAllById(ids);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.item.ItemRepository#save(fr.training.
	 * samples.spring.shop.domain.item.Item)
	 */
	@Override
	public void save(final Item item) {
		itemJpaRepository.save(item);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.training.samples.spring.shop.domain.item.ItemRepository#findAll()
	 */
	@Override
	public List<Item> findAll() {
		return itemJpaRepository.findAll();
	}

}
