package com.dharbar.gcp.storage.spring.court.controller.model;

import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@Value
public class ClientUser {

	private Long id;

	private String name;

	public static ClientUser of(User user) {
		return ClientUser.of(user.getId(), user.getName());
	}
}
