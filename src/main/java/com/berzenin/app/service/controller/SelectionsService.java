package com.berzenin.app.service.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.berzenin.app.dao.PhotoRepository;
import com.berzenin.app.model.Photo;

@Service
public class SelectionsService extends GenericServiceImpl<Photo, PhotoRepository> {

	public SelectionsService(PhotoRepository repository, PhotoService photoService) {
		super(repository);
	}

	public List<LocalDate> getDatesBetweenTwoDates(LocalDate dateStartSearch, LocalDate dateFinishSearch) {
	    long numOfDaysBetween = ChronoUnit.DAYS.between(dateStartSearch, dateFinishSearch);	    
	    List<LocalDate> list =  IntStream.iterate(0, i -> i + 1)
	      .limit(numOfDaysBetween)
	      .mapToObj(i -> dateStartSearch.plusDays(i))
	      .collect(Collectors.toList());	
	    return list;
	}
	
	public List<LocalDate> getDatesBetweenDatePlus6Days(LocalDate dateStartSearch) {
	    long numOfDaysBetween = ChronoUnit.DAYS.between(dateStartSearch, dateStartSearch.plusDays(7));	    
	    List<LocalDate> list =  IntStream.iterate(0, i -> i + 1)
	      .limit(numOfDaysBetween)
	      .mapToObj(i -> dateStartSearch.plusDays(i))
	      .collect(Collectors.toList());	
	    return list;
	}

}