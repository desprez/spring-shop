package fr.training.samples.spring.shop.application.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ItemServiceImpl.class })
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;

	@MockBean
	private ItemRepository itemRepositoryMock;

	@Test
	public void addItem_should_call_save_repository_1_time() {
		// Given
		final Item item = new Item();
		item.setDescription("Banana");
		item.setPrice(10);

		// When
		final Item expected = itemService.addItem(item);

		// Then
		assertThat(expected).isNotNull();
		verify(itemRepositoryMock, times(1)).save(item);
	}

	@Test
	public void findAll_should_return_item_list() {
		// Given
		final Item item = new Item();
		item.setDescription("Banana");
		item.setPrice(10);
		final List<Item> items = new ArrayList<>();
		items.add(item);
		when(itemRepositoryMock.findAll()).thenReturn(items);

		// When
		final List<Item> expected = itemService.getAllItems();

		// Then
		assertThat(expected).hasSize(1);
	}

}
