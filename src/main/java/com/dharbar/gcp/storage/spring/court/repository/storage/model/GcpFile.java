package com.dharbar.gcp.storage.spring.court.repository.storage.model;

import com.google.cloud.storage.Blob;
import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class GcpFile {

	String id;

	String name;

	Long size;

	Long deleteTime;

	Long updateTime;

	Long createTime;

	String contentType;

	String contentEncoding;

	public static GcpFile of(Blob blob) {
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
