package fr.training.samples.spring.shop.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataJpaRepository extends JpaRepository<Customer, String> {

}
