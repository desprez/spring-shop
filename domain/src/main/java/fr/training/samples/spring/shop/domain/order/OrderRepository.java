package fr.training.samples.spring.shop.domain.order;

public interface OrderRepository {

	Orders findById(String orderId);

	void save(Orders order);

}
