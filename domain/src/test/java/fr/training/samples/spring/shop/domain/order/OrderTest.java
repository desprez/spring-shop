package fr.training.samples.spring.shop.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;

public class OrderTest {

	@Test
	public void addItem_should_increment_total_with_item_price() {
		// Given
		final Customer customer = Customer.builder().name("Michel Martin").password("password").build();
		final Item banana = Item.builder().description("Bananas").price(10).build();
		final Order order = Order.builder().customer(customer).addOrderItem(new OrderItem(banana, 1)).build();

		// when
		final Item papaya = Item.builder().description("Papayas").price(5).build();
		order.addOrderItem(new OrderItem(papaya, 1));

		// Then
		assertThat(order.getTotal()).isEqualTo(15);
	}
}
