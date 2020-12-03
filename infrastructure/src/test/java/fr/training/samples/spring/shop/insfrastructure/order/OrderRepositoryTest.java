package fr.training.samples.spring.shop.insfrastructure.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import fr.training.samples.spring.shop.domain.order.Orders;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void existing_order_should_be_found() {
		// Given existing order in db
		final String orderId = "123e4567-189b-42d3-a456-556642440000";

		// When
		final Orders orders = orderRepository.findById(orderId);

		// Then
		assertThat(orders).isNotNull();
		assertThat(orders.getId()).isEqualTo(orderId);
		assertThat(orders.getCustomer().getName()).isEqualTo("NAME1");
		assertThat(orders.getItems()).hasSize(2);
	}

	@Test
	public void save_new_order_should_success() {
		// Given
		final Orders order = new Orders();

		final Customer customer = customerRepository.findById("123e4567-e89b-42d3-a456-556642440000");
		order.setCustomer(customer);
		final Item item = itemRepository.findById("123e4567-e89b-42d3-a456-556642440005");
		order.addItem(item);
		// When
		orderRepository.save(order);
		// Then
		assertThat(orderRepository.findById(order.getId())).isNotNull();
	}

}
