package fr.training.samples.spring.shop.infrastructure.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final OrderJpaRepository orderJpaRepository;

	public OrderRepositoryImpl(final OrderJpaRepository orderJpaRepository) {
		this.orderJpaRepository = orderJpaRepository;
	}

	@Override
	public Order findById(final String orderId) {
		return orderJpaRepository.findById(orderId).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public void save(final Order order) {
		orderJpaRepository.save(order);
	}

	@Override
	public List<Order> findByCustomerId(final String customerId) {
		return orderJpaRepository.findByCustomerId(customerId);
	}

}
