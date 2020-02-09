package com.dharbar.gcp.storage.spring.court.controller.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ClientDocument {

	private Long id;

	private String contentType;

	private Long createdDate;

	private String fileName;

	private ClientUser user;
}
