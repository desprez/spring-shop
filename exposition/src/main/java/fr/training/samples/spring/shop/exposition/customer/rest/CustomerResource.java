package fr.training.samples.spring.shop.exposition.customer.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import fr.training.samples.spring.shop.exposition.common.ErrorModel;
import fr.training.samples.spring.shop.exposition.order.rest.OrderDto;
import fr.training.samples.spring.shop.exposition.order.rest.OrderMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class CustomerResource {

	private final CustomerService customerService;

	private final CustomerMapper customerMapper;

	private final OrderService orderService;

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

	@ApiOperation(value = "This operation allow to find customer by his number", nickname = "getCustomer", notes = "Please give customer number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@GetMapping(value = "/customers/{id}", produces = { "application/json" })
	public CustomerDto getCustomer(@ApiParam(value = "id", required = true) @PathVariable("id") final String customerId) {

		final Customer customer = customerService.findOne(customerId);
		return customerMapper.mapToDto(customer);

	}

	@ApiOperation(value = "This operation allow to add a new customer", nickname = "addCustomer", notes = "Please give customer infos")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Customer was added"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@PostMapping(value = "/customers", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> addCustomerUsingPost(@Valid @RequestBody final CustomerLightDto customerLightDto) {

		final Customer customer = customerMapper.mapToEntity(customerLightDto);

		customerService.create(customer);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "This operation allow to update an existing customer", nickname = "updateCustomer", notes = "Please give customer infos to update")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Customer was updated"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@PutMapping(value = "/customers", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<URI> updateCustomerUsingPut(@Valid @RequestBody final CustomerDto CustomerDto) {

		final Customer customer = customerMapper.mapToEntity(CustomerDto);
		customerService.update(customer);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "This operation allow to find customer by his name", nickname = "retrieveCustomerByName", notes = "Please give a customer name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@GetMapping(value = "/customers", produces = { "application/json" })
	public CustomerDto retrieveCustomerByName(@RequestParam final String name) {

		final Customer customer = customerService.findByName(name);
		return customerMapper.mapToDto(customer);
	}

	@ApiOperation(value = "This operation allow to retrieve Customer's orders", nickname = "getOrders", notes = "Please give customer number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	/**
	 * En doublon avec la méthode OrderResource.getOrders(String)
	 */
	@GetMapping(value = "/customers/{id}/orders", produces = { "application/json" })
	public List<OrderDto> getOrders(@ApiParam(value = "id", required = true) @PathVariable("id") final String customerId) {

		final List<Order> orders = orderService.getOrdersForCustomer(customerId);
		return orderMapper.mapToDtoList(orders);
	}

}
