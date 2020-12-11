package fr.training.samples.spring.shop.domain.order;

import java.util.List;

public interface OrderRepository {

	public Order findById(String orderId);

	public void save(Order order);

	public List<Order> findByCustomerId(String customerId);

}
