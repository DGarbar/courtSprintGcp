package com.dharbar.gcp.storage.spring.court.controller;

import com.dharbar.gcp.storage.spring.court.controller.model.ClientDocument;
import com.dharbar.gcp.storage.spring.court.service.GcpService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class DocumentController {

	private GcpService gcpService;

	@GetMapping(value = "/documents")
	public List<ClientDocument> getAll() {
		return gcpService.getDocuments();
	}

	@PostMapping(value = "/document/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void putFile(
		@PathVariable("userId") Long userId,
		@RequestParam("file") MultipartFile file) throws IOException {
		gcpService
			.saveFile(userId, file.getOriginalFilename(), file.getBytes(), file.getContentType());
	}

	@GetMapping("/document/sync/{userId}")
	public void syncFile(@PathVariable("userId") Long userId) {
		gcpService.syncFiles(userId);
	}
}
