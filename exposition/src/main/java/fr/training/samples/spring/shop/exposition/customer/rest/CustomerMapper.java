package fr.training.samples.spring.shop.exposition.customer.rest;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;

/**
 * Mapper for the entity Customer and its DTO CustomeDTO.
 */
@Component
public class CustomerMapper extends AbstractMapper<CustomerDto, Customer> {

	@Override
	public CustomerDto mapToDto(final Customer entity) {
		if (entity == null) {
			return null;
		}
		final CustomerDto dto = new CustomerDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		return dto;
	}

	@Override
	public Customer mapToEntity(final CustomerDto dto) {
		final Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setPassword(dto.getPassword());
		customer.setId(dto.getId());
		return customer;
	}

	public Customer mapToEntity(@Valid final CustomerLightDto dto) {
		final Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setPassword(dto.getPassword());
		return customer;
	}
}