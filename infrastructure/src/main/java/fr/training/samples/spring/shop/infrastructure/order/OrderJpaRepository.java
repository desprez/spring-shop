package fr.training.samples.spring.shop.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.samples.spring.shop.domain.order.Orders;

public interface OrderJpaRepository extends JpaRepository<Orders, String> {

}
