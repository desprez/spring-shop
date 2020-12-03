package fr.training.samples.spring.shop.exposition.item.rest;

import org.springframework.stereotype.Component;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;

/**
 * Mapper for the entity Item and its DTO ItemDto.
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
		final Item entity = new Item();
		entity.setId(dto.getItemId());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		return entity;
	}

	public Item mapToEntity(final ItemLightDto dto) {
		final Item entity = new Item();
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		return entity;
	}

}