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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

		Document document = DocumentMapper.toEntity(fileName, gcpFile, user);
		documentRepository.save(document);
	}

	@Transactional
	public void syncFiles(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found " + userId));

		List<GcpFile> gcpFiles = storageRepository.get(user.getId(), user.getName());
		List<Document> documents = documentRepository.findByUser(user);

		deleteGcpMismatch(gcpFiles, documents);

		deleteDbMismatch(gcpFiles, documents);
	}

	private void deleteGcpMismatch(List<GcpFile> gcpFiles, List<Document> documents) {
		List<String> dbGcpNames = documents.stream()
			.map(Document::getGcpName)
			.collect(Collectors.toList());

		List<String> notInDbGcpFileNames = gcpFiles.stream()
			.map(GcpFile::getName)
			.filter(gcpFileName -> !dbGcpNames.contains(gcpFileName))
			.collect(Collectors.toList());

		if (!notInDbGcpFileNames.isEmpty()) {
			log.info("Founded gcp files that don't have corresponding in db: {}", notInDbGcpFileNames);
			storageRepository.delete(notInDbGcpFileNames);
		}
	}

	private void deleteDbMismatch(List<GcpFile> gcpFiles, List<Document> documents) {
		List<String> gcpFileNames = gcpFiles.stream()
			.map(GcpFile::getName)
			.collect(Collectors.toList());

		List<Document> notInGcpDocuments = documents.stream()
			.filter(document -> !gcpFileNames.contains(document.getGcpName()))
			.collect(Collectors.toList());

		if (!notInGcpDocuments.isEmpty()) {
			log.info("Founded db documents that don't have corresponding in GCP: {}",
				notInGcpDocuments);
			documentRepository.deleteAll(notInGcpDocuments);
		}
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
