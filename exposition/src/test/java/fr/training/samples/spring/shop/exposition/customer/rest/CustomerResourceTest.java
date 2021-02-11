package fr.training.samples.spring.shop.exposition.customer.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.training.samples.spring.shop.application.customer.CustomerService;
import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.exposition.order.rest.OrderMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerResource.class)
@AutoConfigureMockMvc(addFilters = false)
@EnableAutoConfiguration
public class CustomerResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private OrderService orderService;

	@MockBean
	private CustomerMapper customerMapper;

	@MockBean
	private OrderMapper orderMapper;

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void get_customer_should_success() throws JsonProcessingException, Exception {
		// Given
		final String id = "1234567";
		final CustomerDto customerDto = new CustomerDto();
		customerDto.setName("name");
		customerDto.setPassword("password");
		when(customerMapper.mapToDto(any())).thenReturn(customerDto);

		// When
		final String result = mockMvc.perform(get("/api/customers/{id}", id) //
				.accept(MediaType.APPLICATION_JSON)) //
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// Then
		assertThat(result).isEqualTo("{\"id\":null,\"name\":\"name\",\"password\":\"password\"}");
	}

	@Test
	public void create_customer_should_success() throws JsonProcessingException, Exception {
		// Given
		final String id = "1234567";
		final CustomerLightDto customerDto = new CustomerLightDto();
		customerDto.setName("name");
		customerDto.setPassword("password");
		when(customerMapper.mapToEntity(any(CustomerLightDto.class))).thenCallRealMethod();

		// When
		mockMvc.perform(post("/api/customers", id).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customerDto)))
		// Then
		.andExpect(status().isCreated());
	}

	@Test
	public void update_customer_should_success() throws JsonProcessingException, Exception {
		// Given
		final String id = "1234567";
		final CustomerLightDto customerDto = new CustomerLightDto();
		customerDto.setName("name");
		customerDto.setPassword("password");
		when(customerMapper.mapToEntity(any(CustomerLightDto.class))).thenCallRealMethod();

		// When
		mockMvc.perform(put("/api/customers/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customerDto)))
		// Then
		.andExpect(status().isCreated());
	}

}
