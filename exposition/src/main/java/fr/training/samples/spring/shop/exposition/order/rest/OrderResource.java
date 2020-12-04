package fr.training.samples.spring.shop.exposition.order.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

	/**
	 * Constructor for Bean injection
	 */
	public OrderResource(final OrderService orderService, final OrderMapper orderMapper) {
		this.orderService = orderService;
		this.orderMapper = orderMapper;
	}

	@PostMapping(value = "/orders", consumes = { "application/json" })
	public ResponseEntity<URI> addOrder(@Valid @RequestBody final OrderLightDto orderDto) {

		final Order order = orderService.addOrder(orderDto.getCustomerId(), orderDto.getItemIds());

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping(value = "/orders", produces = { "application/json" })
	public List<OrderDto> getOrders(@RequestParam final String customerId) {

		final List<Order> order = orderService.getOrdersForCustomer(customerId);

		return orderMapper.mapToDtoList(order);
	}

}