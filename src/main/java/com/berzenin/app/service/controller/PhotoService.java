package com.berzenin.app.service.controller;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.berzenin.app.dao.PhotoRepository;
import com.berzenin.app.model.Photo;
import com.berzenin.app.service.utils.LocalFilesController;

@Service
public class PhotoService extends GenericServiceImpl<Photo, PhotoRepository> {

	public PhotoService(PhotoRepository repository) {
		super(repository);
	}
	
	@Autowired
	LocalFilesController controller;

	public Optional<Photo> add(MultipartFile file) {
		Photo photo = null;
		try {
			Path path = controller.copyFileForlocalDirectory(file).get();
			photo = new Photo();
			photo.setName(file.getName());
			photo.setDate(LocalDate.of(2000, 1, 1));
			photo.setPathFoPhoto(path.toString());
			repository.save(photo);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return Optional.of(photo);
	}
	
	public Optional<Photo> add(Photo photo, MultipartFile file) {
		try {
			Path path = controller.getPathForPhoto(photo, file).get();
			photo.setFile(path.toFile());
			photo.setTime(LocalTime.now()); 			
			photo.setPathFoPhoto(path.toString().replace("..\\Server-for-photo\\src\\main\\webapp", ""));
//			photo.setPathFoPhoto(path.toString());
			repository.save(photo);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return Optional.of(photo);
	}

}
