package fr.training.spring.shop.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.training.spring.shop.web.dto.ItemDTO;
import fr.training.spring.shop.web.dto.OrderLightDTO;
import fr.training.spring.shop.web.exception.TechnicalException;
import fr.training.spring.shop.web.model.OrderModel;

@Controller
@Secured({ "ROLE_NORMAL_USER", "ROLE_ADMIN" })
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring.shop.showItems.url}")
	private String showItemsUrl;

	@Value("${spring.shop.addOrder.url}")
	private String addOrderUrl;

	@GetMapping("/addOrder")
	public ModelAndView showAddOrder(@ModelAttribute("orderModel") final OrderModel orderModel) {
		final ItemDTO[] response = restTemplate.getForObject(showItemsUrl, ItemDTO[].class);
		final List<ItemDTO> items = Arrays.asList(response);

		orderModel.setItems(items);
		return new ModelAndView("addOrder", "orderModel", orderModel);
	}

	@PostMapping(value = "/addOrder")
	@Secured({ "ROLE_NORMAL_USER", "ROLE_ADMIN" })
	public ModelAndView addOrder(@Valid final OrderModel orderModel, final BindingResult bindingResult) {
		final Set<String> itemIDs = orderModel.getItemIds();

		if (itemIDs == null || itemIDs.isEmpty()) {
			bindingResult.rejectValue("itemIds", null, "no items selected!");
		}
		if (StringUtils.isBlank(orderModel.getCustomerId())) {
			bindingResult.rejectValue("customerId", null, "no customerId has been set!");
		}

		if (bindingResult.hasErrors()) {
			return showAddOrder(orderModel);
		}

		final OrderLightDTO order = new OrderLightDTO(orderModel.getCustomerId().toString());
		final HashSet<String> strs = new HashSet<>(itemIDs.size());
		itemIDs.forEach(i -> strs.add(i.toString()));

		order.setItemIds(strs);

		try {
			restTemplate.postForObject(addOrderUrl, order, OrderLightDTO.class);
		} catch (final TechnicalException e) {
			bindingResult.rejectValue("customerId", null, e.getMessage());
			return showAddOrder(orderModel);
		}

		return new ModelAndView(new RedirectView("addOrder"));
	}

}
