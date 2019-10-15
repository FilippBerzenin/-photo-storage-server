package com.berzenin.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.app.model.Photo;
import com.berzenin.app.service.controller.PhotoService;
import com.berzenin.app.service.controller.SelectionsService;

@Controller
public class InnerSelectionViewController extends GenericViewControllerImpl<Photo, SelectionsService> {

	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value = "/reports/merch_report", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String getRezultForMerch(@RequestParam("photos_list") String photo, Model model) {
		List<Photo> photos = new ArrayList<>();
		Pattern pattern = Pattern.compile("id=.+?\\D");
		Matcher matcher = pattern.matcher(photo);
		while (matcher.find()) {
			long i = Long.parseLong(photo.substring(matcher.start() + 3, matcher.end() - 1));
			photos.add(photoService.findById(i));
		}
		model.addAttribute("potos_list", photos);
		model.addAttribute("central_image", photos.get(0));
		model.addAttribute("page", "photos_merch");
		
		return "photos_merch";
	}
	
	@RequestMapping(value = "/reports/merch_report/{photo_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String getPhotoForMerch(
			@RequestParam("photos_list") String photo,
			@PathVariable("photo_id") long photo_id,			
			Model model) {
		List<Photo> photos = new ArrayList<>();
		Pattern pattern = Pattern.compile("id=.+?\\D");
		Matcher matcher = pattern.matcher(photo);
		while (matcher.find()) {
			long i = Long.parseLong(photo.substring(matcher.start() + 3, matcher.end() - 1));
			photos.add(photoService.findById(i));		}
		model.addAttribute("potos_list", photos);
		model.addAttribute("central_image", photoService.findById(photo_id));
		model.addAttribute("page", "photos_merch");		
		return "photos_merch";
	}
	
	@RequestMapping(value = "/reports/shop_report/{photo_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String getPhotoForShop(
			@RequestParam("photos_list") String photo,
			@PathVariable("photo_id") long photo_id,			
			Model model) {
		List<Photo> photos = new ArrayList<>();
		Pattern pattern = Pattern.compile("id=.+?\\D");
		Matcher matcher = pattern.matcher(photo);
		while (matcher.find()) {
			long i = Long.parseLong(photo.substring(matcher.start() + 3, matcher.end() - 1));
			photos.add(photoService.findById(i));		}
		model.addAttribute("potos_list", photos);
		model.addAttribute("central_image", photoService.findById(photo_id));
		model.addAttribute("page", "photos_shop");		
		return "photos_shop";
	}
	
	@RequestMapping(value = "/reports/shop_report", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String getRezultForShop(@RequestParam("photos_list") String photo, Model model) {
		List<Photo> photos = new ArrayList<>();
		Pattern pattern = Pattern.compile("id=.+?\\D");
		Matcher matcher = pattern.matcher(photo);
		while (matcher.find()) {
			long i = Long.parseLong(photo.substring(matcher.start() + 3, matcher.end() - 1));
			photos.add(photoService.findById(i));
		}
		model.addAttribute("potos_list", photos);
		model.addAttribute("central_image", photos.get(0));
		
		return "photos_shop";
	}


}
