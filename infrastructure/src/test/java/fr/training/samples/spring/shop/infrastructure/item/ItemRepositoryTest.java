package fr.training.samples.spring.shop.infrastructure.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void existing_item_should_be_found() {
		// Given existing item in db
		final Long itemId = Long.valueOf(56424001);

		// When
		final Item item = itemRepository.findById(itemId);

		// Then
		assertThat(item).isNotNull();
		assertThat(item.getId()).isEqualTo(itemId);
		assertThat(item.getDescription()).isEqualTo("DESC1");
		assertThat(item.getPrice()).isEqualTo(10);
	}

	@Test
	public void save_new_item_should_success() {
		// Given
		final Item item = new Item();
		item.setDescription("Banana");
		item.setPrice(5);

		// When
		itemRepository.save(item);
		entityManager.persistAndFlush(item);

		// Then
		assertThat(itemRepository.findById(item.getId())).isNotNull();
	}

	@Test
	public void findall_should_return_all_items() {
		// Given records from database

		// When
		final List<Item> result = itemRepository.findAll();

		// Then
		assertThat(result).hasSize(5);
	}

}
