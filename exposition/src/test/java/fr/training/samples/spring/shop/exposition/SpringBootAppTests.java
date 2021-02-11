package fr.training.samples.spring.shop.exposition;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.training.samples.spring.shop.exposition.customer.rest.CustomerResource;
import fr.training.samples.spring.shop.exposition.item.rest.ItemResource;
import fr.training.samples.spring.shop.exposition.order.rest.OrderResource;

@SpringBootTest
public class SpringBootAppTests {

	@Autowired
	private CustomerResource customerResource;

	@Autowired
	private OrderResource orderResource;

	@Autowired
	private ItemResource itemResource;

	@Test
	public void contextLoads() {
		assertThat(customerResource).isNotNull();
		assertThat(orderResource).isNotNull();
		assertThat(itemResource).isNotNull();
	}

}