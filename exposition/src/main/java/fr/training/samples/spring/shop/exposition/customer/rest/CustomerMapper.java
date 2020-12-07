package fr.training.samples.spring.shop.exposition.customer.rest;

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
		final CustomerDto dto = new CustomerDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		return dto;
	}

	@Override
	public Customer mapToEntity(final CustomerDto dto) {
		final Customer entity = new Customer();
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		return entity;
	}

	public Customer mapToEntity(final CustomerLightDto dto) {
		final Customer entity = new Customer();
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		return entity;
	}

}