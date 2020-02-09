package com.dharbar.gcp.storage.spring.court.service;

import com.dharbar.gcp.storage.spring.court.controller.model.ClientDocument;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.DocumentRepository;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.UserRepository;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.Document;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import com.dharbar.gcp.storage.spring.court.repository.storage.StorageRepository;
import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import com.dharbar.gcp.storage.spring.court.service.mapper.DocumentMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GcpService {

	private UserRepository userRepository;
	private DocumentRepository documentRepository;
	private StorageRepository storageRepository;

	@Transactional
	public void saveFile(Long userId, String fileName, byte[] data, String contentType) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found " + userId));

		String fileId = createFileId(user, fileName);
		GcpFile gcpFile = storageRepository.put(fileId, data, contentType);

		Document document = DocumentMapper.toEntity(gcpFile, user);
		documentRepository.save(document);
	}

	public List<ClientDocument> getDocuments() {
		return StreamSupport.stream(documentRepository.findAllEagerly().spliterator(), false)
			.map(DocumentMapper::toClient)
			.collect(Collectors.toList());
	}

	private String createFileId(User user, String fileName) {
		return String.format("%s_%s_%s", user.getId(), user.getName(), fileName);
	}
}
