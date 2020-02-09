package com.dharbar.gcp.storage.spring.court.repository.storage.model;

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

}
