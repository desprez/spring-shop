package fr.training.samples.spring.shop.domain.customer;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerDataJpaRepository customerDataJpaRepository;

	public CustomerRepositoryImpl(final CustomerDataJpaRepository customerDataJpaRepository) {
		this.customerDataJpaRepository = customerDataJpaRepository;
	}

	@Override
	public Optional<Customer> findById(final String id) {
		return customerDataJpaRepository.findById(id);
	}

}
