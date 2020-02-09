package com.dharbar.gcp.storage.spring.court.repository.gspsql.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "gcpId")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NaturalId
	private String gcpId;

	private String contentType;

	@Column(nullable = false)
	private Long createdDate;

	@Column(nullable = false)
	private String fileName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Document(String gcpId, String contentType, Long createdDate, String fileName, User user) {
		this.gcpId = gcpId;
		this.contentType = contentType;
		this.createdDate = createdDate;
		this.fileName = fileName;
		this.user = user;
	}
}
