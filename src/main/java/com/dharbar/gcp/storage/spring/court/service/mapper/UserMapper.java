package com.dharbar.gcp.storage.spring.court.service.mapper;

import com.dharbar.gcp.storage.spring.court.controller.model.ClientUser;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import com.google.cloud.storage.Blob;

public class UserMapper {

	public static ClientUser toClient(User user) {
		return ClientUser.of(user.getId(), user.getName());
	}

}
