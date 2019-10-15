package com.berzenin.app.web.dto;

import java.util.List;

import com.berzenin.app.model.Merch;
import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.model.Photo;

import lombok.Data;

@Data
public class MerchReport {
	
	private Merch merch;
	private ObjectPlace objectPlace;
	private List<Photo> photos;
	private long id;

	public MerchReport(Merch merch, ObjectPlace objectPlace, List<Photo> photos) {
		this.objectPlace = objectPlace;
		this.photos = photos;
		this.merch = merch;
		
	}
	
	

}
