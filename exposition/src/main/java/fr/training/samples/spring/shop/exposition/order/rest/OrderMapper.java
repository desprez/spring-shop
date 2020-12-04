package fr.training.samples.spring.shop.exposition.order.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import fr.training.samples.spring.shop.exposition.item.rest.ItemMapper;

/**
 * Mapper for the Order entity and its Dto .
 */
@Component
public class OrderMapper extends AbstractMapper<OrderDto, Order> {

	private final ItemMapper itemMapper;

	public OrderMapper(final ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}

	@Override
	public OrderDto mapToDto(final Order entity) {
		final OrderDto orderDto = new OrderDto();
		orderDto.setId(entity.getId());
		orderDto.setTotal(entity.getTotal());
		if (entity.getCustomer() != null) {
			orderDto.setCustomerId(entity.getCustomer().getId());
		}
		if (!CollectionUtils.isEmpty(entity.getItems())) {
			orderDto.setItems(itemMapper.mapToDtoList(entity.getItems()));
		}
		return orderDto;
	}

	@Override
	public Order mapToEntity(final OrderDto dto) {
		final Order entity = new Order();
		final Customer customerEntity = new Customer();
		customerEntity.setId(dto.getCustomerId());
		entity.setCustomer(customerEntity);
		entity.setId(dto.getId());
		entity.setItems(itemMapper.mapToEntityList(dto.getItems()));
		return entity;
	}

}