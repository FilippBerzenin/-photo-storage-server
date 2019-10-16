package com.berzenin.app.web.controller;

import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class IndexViewController {
	
	@Value("${value.from.logo}")
	protected String logoImg;
	
	@RequestMapping(value = {"/", "/index" }, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String index(Model model) {
		String message = "Abandon hope all ye who enter here";
		model.addAttribute("message", message);
		model.addAttribute("logo", logoImg);
		return "index";
	}
}
