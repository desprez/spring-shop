package fr.training.samples.spring.shop.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void existing_customer_should_be_found() {
		// Given existing customer in db
		final String idCustomer = "123e4567-e89b-42d3-a456-556642440000";

		// When
		final Optional<Customer> customer = customerRepository.findById(idCustomer);

		// Then
		assertThat(customer.isPresent()).isTrue();
		assertThat(customer.get().getId()).isEqualTo("123e4567-e89b-42d3-a456-556642440000");
		assertThat(customer.get().getName()).isEqualTo("NAME1");
	}

}
