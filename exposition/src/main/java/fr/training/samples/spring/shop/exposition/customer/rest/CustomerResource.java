package fr.training.samples.spring.shop.exposition.customer.rest;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.training.samples.spring.shop.application.customer.CustomerService;
import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.order.rest.OrderDto;
import fr.training.samples.spring.shop.exposition.order.rest.OrderMapper;

@RestController
@RequestMapping("/api")
public class CustomerResource {

	private final CustomerService customerService;

	private final OrderService orderService;

	private final CustomerMapper customerMapper;

	private final OrderMapper orderMapper;

	/**
	 * Constructor for Bean injection
	 */
	public CustomerResource(final CustomerService customerService, final CustomerMapper customerMapper,
			final OrderService orderService, final OrderMapper orderMapper) {
		this.customerService = customerService;
		this.customerMapper = customerMapper;
		this.orderService = orderService;

		this.orderMapper = orderMapper;
	}

	@GetMapping(value = "/customers/{id}", produces = { "application/json" })
	public CustomerDto getCustomerById(@PathVariable final String id) {

		final Customer customer = customerService.findOne(id);

		return customerMapper.mapToDto(customer);
	}

	@PostMapping(value = "/customers", consumes = { "application/json" })
	public ResponseEntity<URI> addCustomerUsingPost(@RequestBody final CustomerLightDto customerLightDto) {

		final Customer customer = customerMapper.mapToEntity(customerLightDto);

		customerService.create(customer);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/customer/{id}", consumes = { "application/json" })
	public void updateCustomerUSingPut(@PathVariable final String id, @RequestBody final CustomerDto customerDto) {
		customerDto.setId(id);
		final Customer customer = customerMapper.mapToEntity(customerDto);
		customerService.update(customer);
	}

	@GetMapping(value = "/customers", produces = { "application/json" })
	public CustomerDto retrieveCustomerByName(@RequestParam final String name) {

		final Customer customer = customerService.findByName(name);
		return customerMapper.mapToDto(customer);
	}

	/**
	 * En doublon avec la méthode OrderResource.getOrders(String)
	 */
	@GetMapping(value = "/customers/{id}/orders", produces = { "application/json" })
	public List<OrderDto> getOrders(@PathVariable final String id) {

		final List<Order> orders = orderService.getOrdersForCustomer(id);
		return orderMapper.mapToDtoList(orders);
	}

}
