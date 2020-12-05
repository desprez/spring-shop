package fr.training.samples.spring.shop.infrastructure.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.samples.spring.shop.domain.customer.Customer;

public interface CustomerJpaRepository extends JpaRepository<Customer, String> {

	Customer findByName(String name);

}
