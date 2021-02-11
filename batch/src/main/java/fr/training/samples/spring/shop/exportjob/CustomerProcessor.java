package fr.training.samples.spring.shop.exportjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.training.samples.spring.shop.application.customer.CustomerService;
import fr.training.samples.spring.shop.domain.customer.Customer;

/**
 * ItemProcessor represents the business processing of an item. The data read by
 * ItemReader can be passed on to ItemProcessor. In this unit, the data is
 * transformed and sent for writing. If, while processing the item, it becomes
 * invalid for further processing, you can return null. The nulls are not
 * written by ItemWriter.
 */
@Component
public class CustomerProcessor implements ItemProcessor<String, CustomerDto> {

	private static final Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);

	@Autowired
	private CustomerService customerService;

	@Override
	public CustomerDto process(final String customerId) throws Exception {
		final Customer customer = customerService.findOne(customerId);
		logger.debug("Processing Customer {}", customer);

		final CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());
		if (customer.getPassword() != null) {
			customerDto.setPassword(customer.getPassword().getObfuscatedValue());
		}
		if (customer.getEmail() != null) {
			customerDto.setEmail(customer.getEmail().getValue());
		}

		if (customer.getAddress() != null) {
			customerDto.setStreet(customer.getAddress().getStreet());
			customerDto.setCity(customer.getAddress().getCity());
			customerDto.setCountry(customer.getAddress().getCountry());
			customerDto.setPostalCode(customer.getAddress().getPostalCode());
		}

		return customerDto;
	}

}
