package com.dharbar.gcp.storage.spring.court.config;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.cloud.gcp.core.DefaultCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GcpConfig {

//	@Bean
//	public CredentialsProvider googleCredentials() throws Exception {
//		GoogleCredentialsProvider.newBuilder().;
//	}
//
//	@Bean
//	public Credentials googleCredentials() throws IOException {
//		final FileInputStream path = new FileInputStream(
//			"D:\\Code\\test\\courtSprintGcp\\src\\main\\resources\\secret\\key-storage.json");
//		return GoogleCredentials.fromStream(path);
//	}
//
//	@Bean
//	public Storage storage(Credentials googleCredentials) {
//		return StorageOptions.newBuilder().setCredentials(googleCredentials).build().getService();
//	}

}
