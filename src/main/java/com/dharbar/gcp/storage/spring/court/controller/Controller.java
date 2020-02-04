package com.dharbar.gcp.storage.spring.court.controller;

import com.google.cloud.storage.Storage;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.storage.GoogleStorageResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Value("${bucket-name}")
	private String bucket;

	private Storage storage;

	private ResourceLoader resourceLoader;

	public Controller(Storage storage, ResourceLoader resourceLoader) {
		this.storage = storage;
		this.resourceLoader = resourceLoader;
	}


	@GetMapping(value = "/")
	public ResponseEntity<Resource> readGcsFile() throws IOException {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

		final Resource resource = resourceLoader.getResource("gs://gcpcourt/LolIa.jpg");

		return new ResponseEntity<>(resource,
			httpHeaders, HttpStatus.OK);
	}

//	@GetMapping(value = "/", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public File readGcsFile() throws IOException {
//		new GoogleStorageResource()
//		return resourceLoader.getResource("gs://gcpcourt/LolIa.jpg").;
//	}

//	@PostMapping("/")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file,
//			RedirectAttributes redirectAttributes) {
//
//		storageService.store(file);
//		redirectAttributes.addFlashAttribute("message",
//				"You successfully uploaded " + file.getOriginalFilename() + "!");
//
//		return "redirect:/";
//	}
}
