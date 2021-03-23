
package fr.training.samples.spring.shop.application.order;

import java.util.List;

import fr.training.samples.spring.shop.domain.order.Order;

public interface OrderService {

	/**
	 * Add new order according to the given customer id and items ids.
	 *
	 * @param customerId the customer identifier
	 * @param itemIds    list of items ids
	 * @return then created order
	 */
	public Order addOrder(Long customerId, List<Long> itemIds);

	/**
	 * Get Order according to the given orderId
	 *
	 * @param orderId the order identifier
	 * @return the Order found
	 */
	public Order findOne(Long orderId);

	/**
	 * Retreive all orders for a customer according to the customer id.
	 *
	 * @param customerId the customer identifier
	 * @return the list of Orders submited by the customer
	 */
	public List<Order> getOrdersForCustomer(Long customerId);

}
