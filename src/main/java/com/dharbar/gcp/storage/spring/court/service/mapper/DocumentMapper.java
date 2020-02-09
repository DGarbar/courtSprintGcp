package com.dharbar.gcp.storage.spring.court.service.mapper;

import com.dharbar.gcp.storage.spring.court.controller.model.ClientDocument;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.Document;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;

public class DocumentMapper {

	public static Document toEntity(String fileName, GcpFile gcpFile, User user) {
		return Document.builder()
			.fileName(fileName)
			.gcpName(gcpFile.getName())
			.contentType(gcpFile.getContentType())
			.createdDate(gcpFile.getCreateTime())
			.user(user)
			.build();
	}

	public static ClientDocument toClient(Document document) {
		return ClientDocument.builder()
			.id(document.getId())
			.fileName(document.getFileName())
			.contentType(document.getContentType())
			.createdDate(document.getCreatedDate())
			.user(UserMapper.toClient(document.getUser()))
			.build();
	}
}
