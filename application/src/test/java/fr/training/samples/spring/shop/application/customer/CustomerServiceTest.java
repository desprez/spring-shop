package fr.training.samples.spring.shop.application.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerServiceImpl.class })
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepositoryMock;

	@Test
	public void create_should_call_save_repository_1_time() {
		// Given
		final Customer customer = new Customer();
		customer.setName("Michel Dupont");

		// When
		final Customer result = customerService.create(customer);

		// Then
		assertThat(result).isNotNull();
		verify(customerRepositoryMock, times(1)).save(customer);
	}

	@Test
	public void findOne_should_call_findById_repository_1_time() {
		// Given
		final String customerId = "123e4567-e89b-42d3-a456-556642440000";
		final Customer customer = new Customer();
		customer.setName("Michel Dupont");
		customer.setPassword("password");
		when(customerRepositoryMock.findById(customerId)).thenReturn(customer);

		// When
		final Customer result = customerService.findOne(customerId);

		// Then
		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("Michel Dupont");
		assertThat(result.getPassword()).isEqualTo("password");
		verify(customerRepositoryMock, times(1)).findById(customerId);
	}

	@Test
	public void update_should_call_save_repository_1_time() {
		// Given
		final Customer customer = new Customer();
		customer.setName("Michel Dupont");

		// When
		final Customer result = customerService.create(customer);

		// Then
		assertThat(result).isNotNull();
		verify(customerRepositoryMock, times(1)).save(customer);
	}

}
