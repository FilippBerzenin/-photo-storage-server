package com.berzenin.app.service.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.berzenin.app.model.Photo;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocalFilesController {
	
	@Getter
	protected static final String pathToResource = "..\\Server-for-photo\\src\\main\\webapp\\image";
	
	public Optional<Path> copyFileForlocalDirectory(MultipartFile file) {
		Path copied = Paths.get(pathToResource, file.getOriginalFilename());
		if (Files.notExists(copied)) {
			Files.exists(copied);
		}
		try {
			Files.copy(file.getInputStream(), copied, StandardCopyOption.REPLACE_EXISTING);
			return Optional.of(copied);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<Path> getPathForPhoto(Photo photo, MultipartFile file) {
		try {
			String year = Integer.toString(photo.getDate().getYear());
			String month = Integer.toString(photo.getDate().getMonthValue());
			String day = Integer.toString(photo.getDate().getDayOfMonth());
		Path dir = Paths.get(pathToResource, 
				year, 
				month,
				day,
				photo.getMerch().getName().trim(), 
				photo.getObjectPlace().getName().trim());
				
		if (Files.notExists(dir)) {
			Files.createDirectories(dir);
		}
		else if (Files.exists(Paths.get(dir.toString(), file.getOriginalFilename()))) {
				Files.delete(Paths.get(dir.toString(), file.getOriginalFilename()));
			}
			Path f =  Paths.get(dir.toString(), file.getOriginalFilename());
			Files.copy(file.getInputStream(), f, StandardCopyOption.REPLACE_EXISTING);
			return Optional.of(f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Optional.empty();
	}
	
	public boolean deleteFile(String link) {
		try {
			Files.deleteIfExists(Paths.get(link));
			return true;
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}
	
	public Path getLocalPathForPdf(MultipartFile file) {
		createLocalDirectory();
		Path path = Paths.get(pathToResource + "\\localfiles\\" + file.getOriginalFilename());
		return path;
	}
	
	public void createLocalDirectory() {
		if (!Files.notExists(Paths.get(pathToResource + "\\localfiles\\"))) {
			try {
				Files.createDirectory(Paths.get(pathToResource + "\\localfiles\\"));
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public String setPathForFile(String url) {
		String path = "";
		try {
			URL partOfurl = new URL(url);
			if (!Files.exists(Paths.get(pathToResource + partOfurl.getHost()))) {
				Files.createDirectories(Paths.get(pathToResource + partOfurl.getHost()));
			}
			path = pathToResource + partOfurl.getHost() + "\\";
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return path;
	}

	public Optional<List<String>> readAllLines(String url) {
		try {
			return Optional.of(Files.readAllLines(Paths.get(url)));
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
