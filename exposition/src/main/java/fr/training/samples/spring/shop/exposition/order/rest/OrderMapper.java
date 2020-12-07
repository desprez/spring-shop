package fr.training.samples.spring.shop.exposition.order.rest;

import org.springframework.stereotype.Component;

import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import fr.training.samples.spring.shop.exposition.customer.rest.CustomerMapper;
import fr.training.samples.spring.shop.exposition.item.rest.ItemMapper;

/**
 * Mapper for the Order entity and its Dto .
 */
@Component
public class OrderMapper extends AbstractMapper<OrderDto, Order> {

	private final CustomerMapper customerMapper;

	private final ItemMapper itemMapper;

	public OrderMapper(final CustomerMapper customerMapper, final ItemMapper itemMapper) {
		this.customerMapper = customerMapper;
		this.itemMapper = itemMapper;
	}

	@Override
	public OrderDto mapToDto(final Order entity) {
		final OrderDto dto = new OrderDto();
		dto.setId(entity.getId());
		dto.setCustomer(customerMapper.mapToDto(entity.getCustomer()));
		dto.setItems(itemMapper.mapToDtoList(entity.getItems()));
		dto.setTotal(entity.getTotal());
		return dto;
	}

	@Override
	public Order mapToEntity(final OrderDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}