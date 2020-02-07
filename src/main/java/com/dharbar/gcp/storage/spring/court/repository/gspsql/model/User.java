package com.dharbar.gcp.storage.spring.court.repository.gspsql.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	@NaturalId
	private String name;

	@Builder
	public User(String name) {
		this.name = name;
	}
}
