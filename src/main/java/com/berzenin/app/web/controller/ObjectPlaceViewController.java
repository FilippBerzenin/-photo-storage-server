package com.berzenin.app.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.service.controller.ObjectPlaceService;

@Controller
@RequestMapping(value = "/object_place")
public class ObjectPlaceViewController extends GenericViewControllerImpl<ObjectPlace, ObjectPlaceService> {

	ObjectPlaceViewController(ObjectPlaceService service) {
		page = "object_place";
	}
	
	@Value("${value.from.logo}")
	protected String logoImg;

	@ModelAttribute("entityFor")
	public ObjectPlace getLoginForm() {
		return new ObjectPlace();
	}

	@RequestMapping(name = "/created/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addNewObjectPlace(
			@ModelAttribute("entity") ObjectPlace entity, 
			BindingResult result, 
			Model model) {
		if (result.hasErrors()) {
			message = "Something wrong with parameters";
			setModelAttribute(model);
			model.addAttribute("logo", logoImg);
			return page;
		}
		System.out.println(entity.toString());
		message = "ok"+entity.toString();
		model.addAttribute("logo", logoImg);
		setModelAttribute(model);
		return page;
	}

}
