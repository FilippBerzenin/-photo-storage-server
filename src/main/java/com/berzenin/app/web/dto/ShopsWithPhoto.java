package com.berzenin.app.web.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.berzenin.app.model.Merch;
import com.berzenin.app.model.Photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopsWithPhoto {
	private String name;
	private Map<LocalDate, Map<Merch, List<Photo>>> photos;
	private long id;
	
	public ShopsWithPhoto(Long shop_id, String name, Map<LocalDate, Map<Merch, List<Photo>>> photos) {
		this.name = name;
		this.photos = photos;
		this.id = shop_id;
	}
	
}
