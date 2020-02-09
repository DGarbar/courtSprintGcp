package com.dharbar.gcp.storage.spring.court.repository.gspsql.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "name")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NaturalId
	private String name;

	@Builder
	public User(String name) {
		this.name = name;
	}
}
