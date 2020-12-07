package fr.training.samples.spring.shop.application.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	/**
	 * Constructor for Bean injection
	 */
	public CustomerServiceImpl(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.customer.CustomerService#create(
	 * fr.training.samples.spring.shop.domain.customer.Customer)
	 */
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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.customer.CustomerService#findOne(
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Customer findOne(final String customerId) {
		return customerRepository.findById(customerId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.customer.CustomerService#update(
	 * fr.training.samples.spring.shop.domain.customer.Customer)
	 */
	@Transactional
	@Override
	public void update(final Customer customer) {
		customerRepository.save(customer);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.customer.CustomerService#
	 * findByName(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Customer findByName(final String name) {
		return customerRepository.findByCustomerName(name);
	}

}
