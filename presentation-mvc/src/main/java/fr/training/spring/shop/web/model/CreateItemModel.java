package fr.training.spring.shop.web.model;

import java.io.Serializable;
import java.util.List;

import fr.training.spring.shop.web.dto.ItemDTO;

public class CreateItemModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean reuseItemDTO;

	private ItemDTO item = new ItemDTO();

	private List<ItemDTO> allItemDTOs;

	private Long itemID;

	public Long getItemDTOID() {
		return itemID;
	}

	public void setItemDTOID(Long itemID) {
		this.itemID = itemID;
	}

	public boolean isReuseItemDTO() {
		return reuseItemDTO;
	}

	public void setReuseItemDTO(boolean reuseItemDTO) {
		this.reuseItemDTO = reuseItemDTO;
	}

	public ItemDTO getItemDTO() {
		return item;
	}

	public void setItemDTO(ItemDTO item) {
		this.item = item;
	}

	public List<ItemDTO> getAllItemDTOs() {
		return allItemDTOs;
	}

	public void setAllItemDTOs(List<ItemDTO> allItemDTOs) {
		this.allItemDTOs = allItemDTOs;
	}

}
