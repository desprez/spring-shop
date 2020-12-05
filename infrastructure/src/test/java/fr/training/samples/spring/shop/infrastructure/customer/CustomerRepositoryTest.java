package fr.training.samples.spring.shop.infrastructure.customer;

import static org.assertj.core.api.Assertions.assertThat;

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
		final String customerId = "123e4567-e89b-42d3-a456-556642440000";

		// When
		final Customer customer = customerRepository.findById(customerId);

		// Then
		assertThat(customer).isNotNull();
		assertThat(customer.getId()).isEqualTo(customerId);
		assertThat(customer.getName()).isEqualTo("NAME1");
	}

	@Test
	public void save_new_customer_should_success() {
		// Given
		final Customer customer = new Customer();
		customer.setName("MICHEL DUPONT");
		customer.setPassword("password");
		// When
		customerRepository.save(customer);
		// Then
		assertThat(customerRepository.findById(customer.getId())).isNotNull();
	}

	@Test
	public void existing_customer_should_be_found_by_his_name() {
		// Given
		final String name = "NAME3";

		// When
		final Customer result = customerRepository.findByCustomerName(name);

		// Then
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo("323e4567-e89b-42d3-a456-556642440000");
	}
}
