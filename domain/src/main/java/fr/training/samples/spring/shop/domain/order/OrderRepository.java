package fr.training.samples.spring.shop.domain.order;

import java.util.List;

public interface OrderRepository {

	Order findById(Long orderId);

	void save(Order order);

	List<Order> findByCustomerId(Long customerId);

}
