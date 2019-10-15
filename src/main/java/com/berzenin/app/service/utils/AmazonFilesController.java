package com.berzenin.app.service.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AmazonFilesController {
	
	private AmazonS3 s3Client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3Client = new AmazonS3Client(credentials);
	}

	private File convertMultiPartToFile(MultipartFile file) {
		log.info("Get files with size " + file.getSize());
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}
	
	public Optional<Path> copyFileForlocalDirectory(MultipartFile file) {
		try {
			log.info("File upload started " + file.getOriginalFilename());
			String path = this.getLocalPathForPdf(file).toString().replace("\\", "/");
		    ObjectMetadata metadata = new ObjectMetadata();
		    metadata.setContentLength(file.getSize());
			s3Client.putObject(new PutObjectRequest(bucketName, path, file.getInputStream(), metadata));
			log.info("File set security Permission started " + file.getOriginalFilename());
			final AccessControlList acl = s3Client.getObjectAcl(bucketName, path);
			acl.grantPermission(GroupGrantee.AllUsers, Permission.FullControl);
			log.info("File set security Permission finished " + file.getOriginalFilename());
			s3Client.setObjectAcl(bucketName, path, acl);
			log.info("File upload " + file.getOriginalFilename() + " successful");		
			return Optional.of(Paths.get(path));
		} catch (NullPointerException | AmazonClientException | IOException e) {
			log.error("File upload failed " + file.getOriginalFilename() + e.getLocalizedMessage());
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	public Path getLocalPathForPdf(MultipartFile file) {
		Path path = Paths.get(file.getOriginalFilename());
		return path;
	}
	
	public String getPathTOS3Bukcet (Path path) {
		URL url = s3Client.getUrl(bucketName, path.toString());
		return url.toString();
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
}
