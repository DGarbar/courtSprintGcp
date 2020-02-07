package com.dharbar.gcp.storage.spring.court.controller;

import com.dharbar.gcp.storage.spring.court.service.GcpService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
@AllArgsConstructor
public class DocumentController {

	GcpService gcpService;

	@PostMapping(value = "/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void putFile(
		@PathVariable("userId") Long userId,
		@RequestParam("file") MultipartFile file) throws IOException {

		gcpService.saveFile(userId, file.getOriginalFilename(), file.getBytes());
	}

}
