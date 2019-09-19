package com.berzenin.app.service.controller;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.berzenin.app.dao.PhotoRepository;
import com.berzenin.app.model.ObjectPlace;
import com.berzenin.app.model.Photo;
import com.berzenin.app.model.SmartphonesUser;
import com.berzenin.app.service.utils.AmazonFilesController;
import com.berzenin.app.service.utils.LocalFilesController;

@Service
public class PhotoService extends GenericServiceImpl<Photo, PhotoRepository> {

	public PhotoService(PhotoRepository repository) {
		super(repository);
	}
	
	@Autowired
	AmazonFilesController controller;

	public Optional<Photo> add(MultipartFile file) {
		Photo photo = null;
		try {
			Path path = controller.copyFileForlocalDirectory(file).get();
			photo = new Photo();
			photo.setName(file.getName());
			photo.setData(LocalDate.of(2000, 1, 1));
			photo.setTime(LocalTime.of(07, 40, 00));
			photo.setPathFoPhoto(path.toString());
//			photo.setObjectPlace(new ObjectPlace());
//			photo.setSmartphonesUser(new SmartphonesUser());
			repository.save(photo);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return Optional.of(photo);
	}

}
