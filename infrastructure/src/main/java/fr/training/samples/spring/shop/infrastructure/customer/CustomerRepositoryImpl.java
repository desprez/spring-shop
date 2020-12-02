package fr.training.samples.spring.shop.infrastructure.customer;

import org.springframework.stereotype.Repository;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerJpaRepository customerDataJpaRepository;

	public CustomerRepositoryImpl(final CustomerJpaRepository customerDataJpaRepository) {
		this.customerDataJpaRepository = customerDataJpaRepository;
	}

	@Override
	public Customer findById(final String id) {
		return customerDataJpaRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public void save(final Customer customer) {
		customerDataJpaRepository.save(customer);
	}

}
