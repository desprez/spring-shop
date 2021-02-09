package fr.training.samples.spring.shop.application.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderItem;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

/**
 * These class methods implements Order's Use cases.
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final CustomerRepository customerRepository;

	private final ItemRepository itemRepository;

	/**
	 * Constructor for Bean injection

	 * @param orderRepository the OrderRepository bean
	 * @param customerRepository the CustomerRepository bean
	 * @param itemRepository the ItemRepository bean
	 */
	public OrderServiceImpl(final OrderRepository orderRepository, final CustomerRepository customerRepository,
			final ItemRepository itemRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.itemRepository = itemRepository;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.order.OrderService#addOrder(java.
	 * lang.String, java.util.List)
	 */
	@Transactional
	@Override
	public Order addOrder(final String CustomerId, final List<String> itemIds) {
		final Customer customer = customerRepository.findById(CustomerId);
		final List<Item> items = itemRepository.findById(itemIds);
		final List<OrderItem> orderItems = items.stream().map(item -> new OrderItem(item))
				.collect(Collectors.toList());
		final Order order = Order.builder().customer(customer).orderItems(orderItems).build();
		orderRepository.save(order);
		return order;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.training.samples.spring.shop.application.order.OrderService#findOne(java.
	 * lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public Order findOne(final String orderId) {
		return orderRepository.findById(orderId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.training.samples.spring.shop.application.order.OrderService#
	 * getOrdersForCustomer(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Order> getOrdersForCustomer(final String customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

}
