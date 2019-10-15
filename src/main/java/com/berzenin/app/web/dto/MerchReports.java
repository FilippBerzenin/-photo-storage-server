package com.berzenin.app.web.dto;

import java.util.List;
import java.util.Map;

import com.berzenin.app.model.Merch;
import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.model.Photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchReports {
	
	private Merch merch;
	
	private Map<ObjectPlace, List<Photo>> photos;
	
	public MerchReports(Merch merch, Map<ObjectPlace, List<Photo>> photos) {
		this.merch = merch;
		this.photos = photos;
	}


	
	

}
