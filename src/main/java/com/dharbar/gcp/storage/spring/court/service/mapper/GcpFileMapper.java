package com.dharbar.gcp.storage.spring.court.service.mapper;

import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import com.google.cloud.storage.Blob;

public class GcpFileMapper {

	public static GcpFile toClient(Blob blob) {
		return GcpFile.builder()
			.id(blob.getGeneratedId())
			.name(blob.getName())
			.size(blob.getSize())
			.deleteTime(blob.getDeleteTime())
			.updateTime(blob.getUpdateTime())
			.createTime(blob.getCreateTime())
			.contentType(blob.getContentType())
			.contentEncoding(blob.getContentEncoding())
			.build();
	}

}
