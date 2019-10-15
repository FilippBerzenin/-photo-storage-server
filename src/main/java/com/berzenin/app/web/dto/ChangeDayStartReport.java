package com.berzenin.app.web.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangeDayStartReport {
	
	private String typeReqest;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private String changeDay;
	

}
