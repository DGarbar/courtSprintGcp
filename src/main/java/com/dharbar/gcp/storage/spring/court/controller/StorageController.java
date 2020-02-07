package com.dharbar.gcp.storage.spring.court.controller;

import com.dharbar.gcp.storage.spring.court.repository.storage.StorageRepository;
import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import com.dharbar.gcp.storage.spring.court.service.GcpService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@RestController("/file")
@AllArgsConstructor
public class StorageController {

	private StorageRepository storageRepository;
	private GcpService gcpService;

	@GetMapping
	public List<GcpFile> getNames() {
		return storageRepository.getGcpFiles();
	}

	@GetMapping(value = "/{name}")
	public Resource getFile(@PathVariable("name") String name) {
		return storageRepository.get(name);
	}

	@PostMapping(value = "/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void putFile(
		@PathVariable("userId") Long userId,
		@RequestParam("file") MultipartFile file) throws IOException {

		gcpService.saveFile(userId, file.getOriginalFilename(), file.getBytes());
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
