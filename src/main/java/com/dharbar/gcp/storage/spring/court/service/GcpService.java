package com.dharbar.gcp.storage.spring.court.service;

import com.dharbar.gcp.storage.spring.court.repository.gspsql.DocumentRepository;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.UserRepository;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.Document;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import com.dharbar.gcp.storage.spring.court.repository.storage.StorageRepository;
import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GcpService {

	UserRepository userRepository;
	DocumentRepository documentRepository;
	StorageRepository storageRepository;

	@Transactional
	public void saveFile(Long userId, String fileName, byte[] data) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found " + userId));

		String fileId = createFileId(user, fileName);
		GcpFile gcpFile = storageRepository.put(fileId, data);

		Document document = toEntity(gcpFile, user);
		documentRepository.save(document);
	}

	private String createFileId(User user, String fileName) {
		return String.format("%s_%s_%s", user.getId(), user.getName(), fileName);
	}

	private Document toEntity(GcpFile gcpFile, User user) {
		return Document.builder()
			.gcpId(gcpFile.getId())
			.fileName(gcpFile.getName())
			.contentType(gcpFile.getContentType())
			.createdDate(gcpFile.getCreateTime())
			.user(user)
			.build();
	}

}
