
package fr.training.samples.spring.shop.application.order;

import java.util.Set;

import fr.training.samples.spring.shop.domain.order.Order;

public interface OrderService {

	/**
	 * @param order the order
	 * @return an Order
	 */
	Order addOrder(Order order);

	/**
	 * @param orderID the order id
	 * @return an Order
	 */
	Order findOne(String orderID);

	/**
	 * @param customerID
	 * @return a Set of OrderEntity
	 */
	Set<Order> getOrdersForCustomer(String customerID);

}
