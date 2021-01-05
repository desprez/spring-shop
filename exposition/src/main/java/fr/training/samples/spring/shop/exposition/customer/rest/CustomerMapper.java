package fr.training.samples.spring.shop.exposition.customer.rest;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.EmailAdress;
import fr.training.samples.spring.shop.domain.customer.Password;
import fr.training.samples.spring.shop.domain.customer.PostalAddress;
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
		dto.setPassword(entity.getPassword().getValue());
		return dto;
	}

	@Override
	public Customer mapToEntity(final CustomerDto dto) {
		return Customer.builder() //
				.id(dto.getId()) //
				.name(dto.getName()) //
				.password(Password.of(dto.getPassword())) //
				.build();
	}

	public Customer mapToEntity(@Valid final CustomerLightDto dto) {
		return Customer.builder() //
				.name(dto.getName()) //
				.password(Password.of(dto.getPassword())) //
				.email(EmailAdress.of(dto.getEmail()))
				.address(new PostalAddress(dto.getStreet(), dto.getCity(), dto.getCountry(), dto.getPostalCode()))
				.build();
	}
}