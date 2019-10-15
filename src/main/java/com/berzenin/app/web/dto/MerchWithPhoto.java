package com.berzenin.app.web.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.model.Photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchWithPhoto {
		
		private String name;
		private Map<LocalDate, Map<ObjectPlace, List<Photo>>> photos;
		private long id;
		
		public MerchWithPhoto(Long merch_id, String name, Map<LocalDate, Map<ObjectPlace, List<Photo>>> photos) {
			this.name = name;
			this.photos = photos;
			this.id = merch_id;
		}

}
