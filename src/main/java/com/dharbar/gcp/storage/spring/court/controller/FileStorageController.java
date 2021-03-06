package com.dharbar.gcp.storage.spring.court.controller;

import com.dharbar.gcp.storage.spring.court.repository.storage.StorageRepository;
import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FileStorageController {

	private StorageRepository storageRepository;

	@GetMapping
	public List<GcpFile> getNames() {
		return storageRepository.getAll();
	}

	@GetMapping(value = "/{name}")
	public Resource getFile(@PathVariable("name") String name) {
		return storageRepository.get(name);
	}
}
