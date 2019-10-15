package com.berzenin.app.web.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.app.model.Photo;
import com.berzenin.app.service.controller.MerchService;
import com.berzenin.app.service.controller.ObjectPlaceService;
import com.berzenin.app.service.controller.SelectionsService;
import com.berzenin.app.web.dto.ChangeDayStartReport;
import com.berzenin.app.web.dto.MerchWithPhoto;
import com.berzenin.app.web.dto.SelectionsRequest;
import com.berzenin.app.web.dto.ShopsWithPhoto;
import com.berzenin.app.web.dto.TypeReqest;

@Controller
@RequestMapping(value = "/reports")
public class SelectionsViewController extends GenericViewControllerImpl<Photo, SelectionsService> {

	@Autowired
	private MerchService merchService;
	
	@Autowired
	private ObjectPlaceService shopService;

	public SelectionsViewController(SelectionsService service) {
		page = "reports";
	}

	@ModelAttribute("requestFor")
	public SelectionsRequest getLoginForm() {
		return new SelectionsRequest();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String setStartPage(Model model) {
		model.addAttribute("page", page);
		model.addAttribute("objectTypes", TypeReqest.values());
		return page;
	}
	
	@RequestMapping(value = "/create_request_week", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String getRezultForWeek(
			@ModelAttribute("requestFor") @Valid SelectionsRequest req,
			BindingResult result,
			Model model) {
		try {
		if (req.getDateStartSearch().equals(null) || result.hasErrors()) {
			model.addAttribute("message", "Обратите внимание на дату запроса");
			model.addAttribute("page", page);
			model.addAttribute("objectTypes", TypeReqest.values());
			return page;
		}
		} catch (RuntimeException e) {
			e.printStackTrace();
			model.addAttribute("message", "Обратите внимание на дату запроса");
			model.addAttribute("page", page);
			model.addAttribute("objectTypes", TypeReqest.values());
			return page;
		}
		List<LocalDate> dates = service.getDatesBetweenDatePlus6Days(req.getDateStartSearch());
		if (req.getTypeReqest().equals(TypeReqest.MERCH.getValue())) {
			model.addAttribute("page", "report_merch");
			List<MerchWithPhoto> merchs = merchService.findAll().stream()
					.map(m -> new MerchWithPhoto(m.getId(), m.getName(), merchService.getPhotosByDates(m, dates)))
					.collect(Collectors.toList());			
			model.addAttribute("listOfMerchsPhoto", merchs);
		}
		if (req.getTypeReqest().equals(TypeReqest.SHOP.getValue())) {
			model.addAttribute("page", "report_shop");
			List<ShopsWithPhoto> shops = shopService.findAll().stream()
					.map(m -> new ShopsWithPhoto(m.getId(), m.getName(), shopService.getPhotosByDates(m, dates)))
					.collect(Collectors.toList());			
			model.addAttribute("listOfShopsPhoto", shops);
		}
		model.addAttribute("type_request", req.getTypeReqest());
		model.addAttribute("listOfDates", dates);
		model.addAttribute("page", page);
		model.addAttribute("objectTypes", TypeReqest.values());
		return page;
	}
	
	@ModelAttribute("changedaystartreport")
	public ChangeDayStartReport getLoginFormChangeDayStartReport() {
		return new ChangeDayStartReport();
	}
	
	@RequestMapping(value = "/create_request_week__change_day", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getRezultForWeekMinusday(
			@ModelAttribute("changedaystartreport") ChangeDayStartReport req,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Обратите внимание на период между датами запроса(не более 30 дней)");
			model.addAttribute("page", page);
			model.addAttribute("objectTypes", TypeReqest.values());
			return page;
		}
		LocalDate newDate;
		if (req.getChangeDay().equals("plus")) {
			newDate = req.getDate().plusDays(1);
		} else {
			newDate = req.getDate().minusDays(1);	
		}
		List<LocalDate> dates = service.getDatesBetweenDatePlus6Days(newDate);
		if (req.getTypeReqest().equals(TypeReqest.MERCH.getValue())) {
			List<MerchWithPhoto> merchs = merchService.findAll().stream()
					.map(m -> new MerchWithPhoto(m.getId(), m.getName(), merchService.getPhotosByDates(m, dates)))
					.collect(Collectors.toList());
			model.addAttribute("listOfMerchsPhoto", merchs);
		}
		if (req.getTypeReqest().equals(TypeReqest.SHOP.getValue())) {
			List<ShopsWithPhoto> shops = shopService.findAll().stream()
					.map(m -> new ShopsWithPhoto(m.getId(), m.getName(), shopService.getPhotosByDates(m, dates)))
					.collect(Collectors.toList());
			model.addAttribute("listOfShopsPhoto", shops);
		}
		model.addAttribute("type_request", req.getTypeReqest());
		model.addAttribute("listOfDates", dates);
		model.addAttribute("page", page);
		model.addAttribute("objectTypes", TypeReqest.values());
		return page;
	}
}
