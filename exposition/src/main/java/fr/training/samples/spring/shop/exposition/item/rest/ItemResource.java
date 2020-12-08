package fr.training.samples.spring.shop.exposition.item.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.training.samples.spring.shop.application.item.ItemService;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.common.ErrorModel;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Validated
public class ItemResource {

	private static final Logger logger = LoggerFactory.getLogger(ItemResource.class);

	private final ItemService itemService;

	private final ItemMapper itemMapper;

	/**
	 * Constructor for Bean injection
	 */
	public ItemResource(final ItemService itemService, final ItemMapper itemMapper) {
		this.itemService = itemService;
		this.itemMapper = itemMapper;
	}

	@ApiOperation(value = "This operation allow to retrieve all items", nickname = "getAllItems", notes = "All existing items should be returned")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ItemDto.class, responseContainer = "List"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found ", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@GetMapping(value = "/items", produces = { "application/json" })
	@Timed
	public List<ItemDto> getAllItemsUsingGet() {

		final List<Item> items = itemService.getAllItems();
		logger.info("Number of items returned: {}", items.size());
		return itemMapper.mapToDtoList(items);

	}

	@ApiOperation(value = "This operation allow to add a new item", nickname = "addItem", notes = "Please give item infos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Item was added"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found ", response = ErrorModel.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
	@PostMapping(value = "/items", produces = { "application/json" })
	@Timed
	public ResponseEntity<URI> addItemUsingPost(@Valid @RequestBody final ItemLightDto itemDto) {

		final Item item = itemMapper.mapToEntity(itemDto);
		itemService.addItem(item);
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}