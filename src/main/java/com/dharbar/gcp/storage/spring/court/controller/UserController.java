package com.dharbar.gcp.storage.spring.court.controller;

import com.dharbar.gcp.storage.spring.court.controller.model.ClientUser;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.UserRepository;
import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

	private UserRepository userRepository;

	@GetMapping(value = "/users")
	public List<ClientUser> getUsers() {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false)
			.map(ClientUser::of)
			.collect(Collectors.toList());
	}

	@GetMapping(value = "/user/{id}")
	public ClientUser getById(@PathVariable("id") long id) {
		return userRepository.findById(id)
			.map(ClientUser::of)
			.orElseThrow(IllegalArgumentException::new);
	}

	@PostMapping(value = "/user")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody ClientUser clientUser) {
		userRepository.save(User.builder().name(clientUser.getName()).build());
	}
}
