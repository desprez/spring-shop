package fr.training.samples.spring.shop.infrastructure.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.samples.spring.shop.domain.order.Order;

public interface OrderJpaRepository extends JpaRepository<Order, String> {

	List<Order> findByCustomerId(String customerId);

}
