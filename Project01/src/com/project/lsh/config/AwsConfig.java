package com.project.lsh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@PropertySource("/WEB-INF/properties/aws.properties")
public class AwsConfig {
	@Value("${aws.accessKey}")
	private String aws_accessKey;
	@Value("${aws.secretKey}")
	private String aws_secretKey;
	
	@Bean
	public BasicAWSCredentials AwsCredentials() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(aws_accessKey, aws_secretKey);
		return awsCreds;
	}
	
	@Bean
	public AmazonS3 AwsS3Client() {
		AmazonS3 s3Builder = AmazonS3ClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(this.AwsCredentials()))
				.build();
		
		return s3Builder;
	}
}
