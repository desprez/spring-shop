package fr.training.samples.spring.shop.domain.customer;

import java.util.Optional;

public interface CustomerRepository {

	public Optional<Customer> findById(String id);

}
