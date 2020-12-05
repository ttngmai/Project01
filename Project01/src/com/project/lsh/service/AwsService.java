package com.project.lsh.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
@PropertySource("/WEB-INF/properties/aws.properties")
public class AwsService {
	@Autowired
	private AmazonS3 s3Client;
	@Value("${s3.bucketName}")
	private String s3_bucketName;
	
	public String uploadMultipartFile(MultipartFile multipartFile,String bucketKey) {
		ObjectMetadata omd = new ObjectMetadata();
		String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
		
		omd.setContentType(multipartFile.getContentType());
		omd.setContentLength(multipartFile.getSize());
		omd.setHeader("filename",multipartFile.getOriginalFilename());
		
		try {
			s3Client.putObject(
					new PutObjectRequest(s3_bucketName + bucketKey, fileName, multipartFile.getInputStream(), omd));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
}
