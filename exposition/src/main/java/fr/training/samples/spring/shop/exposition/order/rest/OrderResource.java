package fr.training.samples.spring.shop.exposition.order.rest;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.domain.order.Order;

@RestController
@RequestMapping("/api")
public class OrderResource {

	private final OrderService orderService;

	private final OrderMapper orderMapper;

	public OrderResource(final OrderService orderService, final OrderMapper orderMapper) {
		this.orderService = orderService;
		this.orderMapper = orderMapper;
	}

	@PostMapping(value = "/orders", consumes = { "application/json" })
	public ResponseEntity<URI> addOrderUsingPost(@RequestBody final OrderLightDto orderLightDto) {

		final Order order = orderService.addOrder(orderLightDto.getCustomerId(), orderLightDto.getItemIds());

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * En doublon avec la méthode CustomerResource.getOrders(String)
	 */
	@GetMapping(value = "/orders", produces = { "application/json" })
	public List<OrderDto> getOrders(@RequestParam final String customerId) {
		final List<Order> orders = orderService.getOrdersForCustomer(customerId);

		return orderMapper.mapToDtoList(orders);
	}

}