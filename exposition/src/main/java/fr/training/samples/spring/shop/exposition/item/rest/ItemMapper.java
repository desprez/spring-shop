package fr.training.samples.spring.shop.exposition.item.rest;

import org.springframework.stereotype.Component;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;

/**
 * Mapper for the Item Entity and its DTO ItemDto.
 */
@Component
public class ItemMapper extends AbstractMapper<ItemDto, Item> {

	@Override
	public ItemDto mapToDto(final Item entity) {
		final ItemDto dto = new ItemDto();
		dto.setItemId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		return dto;
	}

	@Override
	public Item mapToEntity(final ItemDto dto) {
		return Item.builder().id(dto.getItemId()).description(dto.getDescription()).price(dto.getPrice()).build();
	}

	public Item mapToEntity(final ItemLightDto dto) {
		return Item.builder().description(dto.getDescription()).price(dto.getPrice()).build();

	}

}