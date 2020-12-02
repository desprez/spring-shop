package fr.training.samples.spring.shop.infrastructure.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.samples.spring.shop.domain.customer.Customer;

public interface CustomerDataJpaRepository extends JpaRepository<Customer, String> {

}
