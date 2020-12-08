package fr.training.samples.spring.shop.exposition.order.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.common.ErrorModel;
import fr.training.samples.spring.shop.exposition.item.rest.ItemDto;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Validated
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

	@ApiOperation(value = "This operation allow to add a new order", nickname = "addOrder", notes = "Please give order infos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Item was added"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@PostMapping(value = "/orders", consumes = { "application/json" })
	@Timed
	public ResponseEntity<URI> addOrder(@Valid @RequestBody final OrderLightDto orderDto) {

		final Order order = orderService.addOrder(orderDto.getCustomerId(), orderDto.getItemIds());

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(order.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/**
	 * En doublon avec la méthode CustomerResource.getOrders(String)
	 */
	@ApiOperation(value = "This operation allow to retrieve all customer orders", nickname = "retrieveOrdersByCustomer", notes = "Return customer orders according to the customer number")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ItemDto.class, responseContainer = "List"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@GetMapping(value = "/orders", produces = { "application/json" })
	@Timed
	public List<OrderDto> retrieveOrdersByCustomer(@NotNull @RequestParam final String customerId) {

		final List<Order> order = orderService.getOrdersForCustomer(customerId);

		return orderMapper.mapToDtoList(order);
	}

}