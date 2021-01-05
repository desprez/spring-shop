package fr.training.samples.spring.shop.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.EmailAdress;
import fr.training.samples.spring.shop.domain.customer.Password;
import fr.training.samples.spring.shop.domain.customer.PostalAddress;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderItem;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { OrderServiceImpl.class })
public class OrderServiceImplTest {

	@Autowired
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepositoryMock;

	@MockBean
	private CustomerRepository customerRepositoryMock;

	@MockBean
	private ItemRepository itemRepositoryMock;

	private Order getNewOrder() {
		final List<OrderItem> orderItems = getItems().stream().map(item -> new OrderItem(item))
				.collect(Collectors.toList());
		return Order.builder().customer(getCustomer()).orderItems(orderItems).build();
	}

	private List<Item> getItems() {
		final List<Item> items = new ArrayList<>();
		items.add(Item.builder().description("item 1").price(1).build());
		items.add(Item.builder().description("item 2").price(2).build());
		items.add(Item.builder().description("item 3").price(3).build());
		return items;
	}

	private Customer getCustomer() {
		return Customer.builder() //
				.name("Michel Dupont") //
				.password(Password.of("password")) //
				.email(EmailAdress.of("michel.dupont@gmail.com")) //
				.address(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456")) //
				.build();
	}

	@Test
	public void addOrder_should_call_save_repositories_1_time() {
		// Given
		final String customerId = "123e4567-e89b-42d3-a456-556642440000";
		final List<String> itemIds = Arrays.asList(new String[] { "1", "2", "3" });
		when(customerRepositoryMock.findById(customerId)).thenReturn(getCustomer());
		when(itemRepositoryMock.findById(itemIds)).thenReturn(getItems());
		// When
		final Order result = orderService.addOrder(customerId, itemIds);

		// Then
		assertThat(result).isNotNull();
		assertThat(result.getTotal()).isEqualTo(6);
		verify(orderRepositoryMock, times(1)).save(any());
		verify(customerRepositoryMock, times(1)).findById(customerId);
		verify(itemRepositoryMock, times(1)).findById(itemIds);
	}

	@Test
	public void findOne_should_call_findById_repository_1_time() {
		// Given
		final String orderId = "1234567";
		final Order order = getNewOrder();
		when(orderRepositoryMock.findById(orderId)).thenReturn(order);
		// When
		final Order result = orderService.findOne(orderId);
		System.out.println(result);
		// Then
		assertThat(result).isNotNull();
		assertThat(result.getCustomer().getName()).isEqualTo("Michel Dupont");
		System.out.println(result.getCustomer().getPassword().getValue());
		assertThat(result.getCustomer().getPassword().getValue()).isEqualTo("password");
		verify(orderRepositoryMock, times(1)).findById(orderId);
	}

	@Test
	public void getOrdersForCustomer_should_return_Order_list() {
		// Given
		final String customerId = "123e4567-e89b-42d3-a456-556642440000";
		final List<Order> orders = new ArrayList<>();
		orders.add(getNewOrder());

		when(orderRepositoryMock.findByCustomerId(customerId)).thenReturn(orders);

		// When
		final List<Order> expected = orderService.getOrdersForCustomer(customerId);

		// Then
		assertThat(expected).hasSize(1);
	}

}
