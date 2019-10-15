package com.berzenin.app.web.controller;

import javax.annotation.security.PermitAll;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.berzenin.app.model.Merch;
import com.berzenin.app.service.controller.MerchService;

@Controller
@RequestMapping(value="/merch")
public class MerchViewController extends GenericViewControllerImpl<Merch, MerchService> {
	
	public MerchViewController(MerchService service) {
		page = "merch";
	}
	
	@ModelAttribute("new_merch")
	public Merch getLoginForm() {
		return new Merch();
	}
	
	@PermitAll
	@Override
	public String deleteEntity(
			@PathVariable("id") Long id,
			Model model) {
		return super.deleteEntity(id, model);
	}
	}
