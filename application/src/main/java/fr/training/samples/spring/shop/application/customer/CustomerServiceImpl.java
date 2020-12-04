package fr.training.samples.spring.shop.application.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	@Override
	public Customer create(final Customer customer) {

		final Customer existingCustomer = customerRepository.findByCustomerName(customer.getName());
		if (existingCustomer != null) {
			throw new AlreadyExistingException("A customer with this name already exist");
		}
		customerRepository.save(customer);

		return customer;
	}

	@Transactional(readOnly = true)
	@Override
	public Customer findOne(final String customerId) {
		return customerRepository.findById(customerId);
	}

	@Transactional
	@Override
	public void update(final Customer customer) {
		customerRepository.save(customer);
	}

}
