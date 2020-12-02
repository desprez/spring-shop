package fr.training.samples.spring.shop.insfrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void existing_order_should_be_found() {
		// Given existing order in db
		final String orderId = " ";

		// When
		final Order order = orderRepository.findById(orderId);

		// Then
		assertThat(order).isNotNull();
		assertThat(order.getId()).isEqualTo(orderId);
		assertThat(order.getCustomer().getName()).isEqualTo("NAME1");
		assertThat(order.getItems()).hasSize(1);
	}

	@Test
	public void save_new_order_should_success() {
		// Given
		final Order order = new Order();
		order.setCustomer(customer);
		order.addItem()
		// When
		orderRepository.save(order);
		// Then
		assertThat(orderRepository.findById(order.getId())).isNotNull();
	}

}
