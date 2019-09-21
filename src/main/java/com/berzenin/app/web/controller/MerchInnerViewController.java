package com.berzenin.app.web.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.app.model.Merch;
import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.service.controller.MerchService;
import com.berzenin.app.service.controller.ObjectPlaceService;

@Controller
@RequestMapping(value="/merch_inner")
public class MerchInnerViewController extends GenericViewControllerImpl<Merch, MerchService> {
	
	@Autowired
	private ObjectPlaceService objectPlaceService;
	
	public MerchInnerViewController(MerchService service) {
		page = "merch_inner";
	}
	
	@ModelAttribute("entity")
	public Merch getLoginForm() {
		return new Merch();
	}
	
	@Override
	public String findById(@PathVariable("id") Long id, Model model) {
		try {
			Set<ObjectPlace> objectsList = objectPlaceService.findAll().stream()
					.collect(Collectors.toSet());
			model.addAttribute("objectsList", objectsList);
			Merch merch = service.findById(id);
			model.addAttribute("entity", merch);
			model.addAttribute("page", "merch_inner");
			return page;	
		} catch (RuntimeException e) {
			this.setModelAttributeWhenthrowException(e, model);
			return page;
		}
	}
	
	@RequestMapping(value="/addObject/{merch_id}", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String addObject(
			@PathVariable("merch_id") Long merch_id,
			@RequestParam("object") ObjectPlace object_id,
			Model model) {
		try {
			Set<ObjectPlace> objectsList = objectPlaceService.findAll().stream()
					.collect(Collectors.toSet());
			Merch merch = service.findById(merch_id);
			ObjectPlace objectPlace = objectPlaceService.findById(object_id.getId());
			merch.getObjectPlace().add(objectPlace);
			service.update(merch);
			
			model.addAttribute("objectsList", objectsList);
			model.addAttribute("entity", merch);
			model.addAttribute("page", "merch_inner");
			return page;	
		} catch (RuntimeException e) {
			this.setModelAttributeWhenthrowException(e, model);
			return page;
		}
	}

}
