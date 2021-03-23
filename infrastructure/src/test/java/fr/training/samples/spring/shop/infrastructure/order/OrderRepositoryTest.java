package fr.training.samples.spring.shop.infrastructure.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void existing_order_should_be_found() {
		// Given existing order in db
		final Long orderId = Long.valueOf(189);

		// When
		final Order order = orderRepository.findById(orderId);

		// Then
		assertThat(order).isNotNull();
		assertThat(order.getId()).isEqualTo(orderId);
		assertThat(order.getCustomer().getName()).isEqualTo("NAME1");
		assertThat(order.getItems()).hasSize(2);
	}

	@Test
	public void save_new_order_should_success() {
		// Given
		final Order order = new Order();

		final Customer customer = customerRepository.findById(Long.valueOf(1234567));
		order.setCustomer(customer);
		final Item item = itemRepository.findById(Long.valueOf(56424005));
		order.addItem(item);

		// When
		orderRepository.save(order);
		entityManager.persistAndFlush(order);

		// Then
		assertThat(orderRepository.findById(order.getId())).isNotNull();
	}

	@Test
	public void existing_orders_for_customer_should_be_found() {
		// Given existing order in db
		final Long customerId = Long.valueOf(1234567);

		// When
		final List<Order> results = orderRepository.findByCustomerId(customerId);

		// Then
		assertThat(results).hasSize(2);
	}
}
