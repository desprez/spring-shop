package fr.training.samples.spring.shop.infrastructure.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

/**
 * Order Repository implementation using JPA
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final OrderJpaRepository orderJpaRepository;

	/**
	 * Constructor for Bean injection
	 */
	public OrderRepositoryImpl(final OrderJpaRepository orderJpaRepository) {
		this.orderJpaRepository = orderJpaRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.order.OrderRepository#findById(java.
	 * lang.String)
	 */
	@Override
	public Order findById(final String orderId) {
		return orderJpaRepository.findById(orderId).orElseThrow(() -> new NotFoundException());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.order.OrderRepository#save(fr.training
	 * .samples.spring.shop.domain.order.Order)
	 */
	@Override
	public void save(final Order order) {
		orderJpaRepository.save(order);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.domain.order.OrderRepository#findByCustomerId
	 * (java.lang.String)
	 */
	@Override
	public List<Order> findByCustomerId(final String customerId) {
		return orderJpaRepository.findByCustomerId(customerId);
	}

}
