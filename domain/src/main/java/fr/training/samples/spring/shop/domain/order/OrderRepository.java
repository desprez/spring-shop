package fr.training.samples.spring.shop.domain.order;

public interface OrderRepository {

	Order findById(String orderId);

	void save(Order order);

}
