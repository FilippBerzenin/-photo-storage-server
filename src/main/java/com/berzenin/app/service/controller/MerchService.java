package com.berzenin.app.service.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berzenin.app.dao.MerchRepository;
import com.berzenin.app.model.Merch;
import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.model.Photo;

@Service
public class MerchService extends GenericServiceImpl<Merch, MerchRepository> {

	public MerchService(MerchRepository repository) {
		super(repository);
	}
	
	@Autowired
	private PhotoService photoService;
	
	public Optional<Merch> getMercByLoginAndPass(String login, String pass) {
		return repository.findByLoginAndPass(login, pass);
	}
	
	public Optional<Set<ObjectPlace>> getShopsFromMerch(long id) {
		return Optional.of(repository
				.findById(id).get()
				.getObjectPlace());
	}

	public Map<LocalDate, Map<ObjectPlace, List<Photo>>> getPhotosByDates(Merch merch, List<LocalDate> dates) {
		Map<LocalDate, Map<ObjectPlace, List<Photo>>> photoBydates = new HashMap<>();
		
		for(LocalDate date: dates) {
			Map<ObjectPlace, List<Photo>> photoByObject = merch.getPhotos().stream()
				.filter(photo -> photo.getDate().equals(date))
				.collect(Collectors.groupingBy(Photo::getObjectPlace,
				   Collectors.mapping(
				       photo -> photoService.findById(photo.getId()), Collectors.toList())));
			
			photoBydates.put(date, photoByObject);
		}
		for (LocalDate date: dates) {
			if (!photoBydates.containsKey(date)) {
				photoBydates.put(date, new HashMap<>());
			}
		}
		SortedMap sortedMap = new TreeMap();
		sortedMap.putAll(photoBydates);
		return sortedMap;
	}
	
	public Map<ObjectPlace, List<Photo>> getPhotosByShops(Merch merch, LocalDate date) {
		Map<ObjectPlace, List<Photo>> photoBydates = merch.getPhotos().stream()
				.filter(photo -> date.equals(photo.getDate()))
				.filter(photo -> merch.getObjectPlace().contains(photo.getObjectPlace()))
				.collect(Collectors.groupingBy(Photo::getObjectPlace,
			        Collectors.mapping(
			        		photo -> photoService.findById(photo.getId()), Collectors.toList())));
		return photoBydates;
	}
	
	public Optional<Merch> findByName (String name) {
		return repository.findByName(name);
	}
	
	@Override
	public void removeById(Long id) {
		System.out.println(id);
		Merch entity = this.findById(id);
		Merch arhiv;
		if (this.findByName("arhiv").isPresent()) {
			arhiv = this.findByName("arhiv").get();
		} else {
			arhiv = new Merch();
			arhiv.setName("arhiv");
			arhiv.setLogin("arhiv");
			arhiv.setPass("arhiv");
			this.add(arhiv);
		}
		entity.getPhotos().forEach(photo -> {
			photo.setMerch(arhiv);
			photoService.update(photo);
		});		
		entity.getObjectPlace().forEach(shop -> {
			shop.getMerch().remove(entity);
		});
		entity.setObjectPlace(null);
		this.update(entity);
		super.remove(entity);
	}
}
