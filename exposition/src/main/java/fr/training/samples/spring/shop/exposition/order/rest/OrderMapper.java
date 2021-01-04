package fr.training.samples.spring.shop.exposition.order.rest;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderItem;
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
		final List<OrderItem> orderItems = entity.getOrderItems();
		if (!CollectionUtils.isEmpty(orderItems)) {
			for (final OrderItem orderItem : orderItems) {
				orderDto.getItems().add(itemMapper.mapToDto(orderItem.getItem()));
			}
		}
		return orderDto;
	}

	@Override
	public Order mapToEntity(final OrderDto dto) {
		return null;
	}

}