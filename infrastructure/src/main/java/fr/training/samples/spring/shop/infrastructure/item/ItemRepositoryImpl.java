package fr.training.samples.spring.shop.infrastructure.item;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private final ItemJpaRepository itemJpaRepository;

	public ItemRepositoryImpl(final ItemJpaRepository itemJpaRepository) {
		this.itemJpaRepository = itemJpaRepository;
	}

	@Override
	public Item findById(final String itemId) {
		return itemJpaRepository.findById(itemId).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public List<Item> findById(final List<String> ids) {
		return itemJpaRepository.findAllById(ids);
	}

	@Override
	public void save(final Item item) {
		itemJpaRepository.save(item);
	}

	@Override
	public List<Item> findAll() {
		return itemJpaRepository.findAll();
	}

}
