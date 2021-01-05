package fr.training.samples.spring.shop.infrastructure.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.EmailAdress;
import fr.training.samples.spring.shop.domain.customer.Password;
import fr.training.samples.spring.shop.domain.customer.PostalAddress;

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
		assertThat(customer.getEmail().getValue()).isEqualTo("name1@gmail.com");
	}

	@Test
	public void save_new_customer_should_success() {
		// Given
		final Customer customer = Customer.builder() //
				.name("MICHEL DUPONT")//
				.password(Password.of("password")) //
				.email(EmailAdress.of("michel.dupont@gmail.com")) //
				.address(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456")) //
				.build();

		// When
		customerRepository.save(customer);
		// Then
		final Customer found = customerRepository.findById(customer.getId());
		assertThat(found).isNotNull();
		assertThat(found.getEmail().getValue()).isEqualTo("michel.dupont@gmail.com");
		assertThat(found.getAddress().getStreet()).isEqualTo("10 main street");
		assertThat(found.getAddress().getCity()).isEqualTo("Las Vegas");
		assertThat(found.getAddress().getCountry()).isEqualTo("Eldorado");
		assertThat(found.getAddress().getPostalCode()).isEqualTo("123456");
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
