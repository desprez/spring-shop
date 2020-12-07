package fr.training.samples.spring.shop.infrastructure.customer;

import org.springframework.stereotype.Repository;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

/**
 * Customer Repository implementation using JPA
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerJpaRepository customerDataJpaRepository;

	/**
	 * Constructor for Bean injection
	 */
	public CustomerRepositoryImpl(final CustomerJpaRepository customerDataJpaRepository) {
		this.customerDataJpaRepository = customerDataJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.customer.CustomerRepository#findById(
	 * java.lang.String)
	 */
	@Override
	public Customer findById(final String id) {
		return customerDataJpaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Customer " + id + " Not found"));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.customer.CustomerRepository#save(fr.
	 * training.samples.spring.shop.domain.customer.Customer)
	 */
	@Override
	public void save(final Customer customer) {
		customerDataJpaRepository.save(customer);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.domain.customer.CustomerRepository#
	 * findByCustomerName(java.lang.String)
	 */
	@Override
	public Customer findByCustomerName(final String name) {
		return customerDataJpaRepository.findByName(name);
	}

}
