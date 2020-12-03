
package fr.training.samples.spring.shop.application.order;

import java.util.List;

import fr.training.samples.spring.shop.domain.order.Order;

public interface OrderService {

	/**
	 * Add new order according to the given customer id and items ids.
	 *
	 * @param CustomerId the customerId
	 * @param itemIds    list of items ids
	 * @return new order
	 */
	public Order addOrder(String CustomerId, List<String> itemIds);

	/**
	 * Get Order according to the given orderId
	 *
	 * @param orderId the order id
	 * @return an Order
	 */
	public Order findOne(String orderId);

	/**
	 * Get customer orders according to the given customer id.
	 *
	 * @param customerId the customerId
	 * @return a List of Orders
	 */
	public List<Order> getOrdersForCustomer(String customerId);

}
