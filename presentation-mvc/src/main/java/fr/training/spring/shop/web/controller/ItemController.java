package fr.training.spring.shop.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import fr.training.spring.shop.web.dto.ItemDTO;

@Controller
public class ItemController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring.shop.showItems.url}")
	private String showItemsUrl;

	@RequestMapping("/items")
	public ModelAndView showItems() {
		ItemDTO[] response = restTemplate.getForObject(showItemsUrl, ItemDTO[].class);
		List<ItemDTO> items = Arrays.asList(response);
		return new ModelAndView("items", "items", items);
	}

}
