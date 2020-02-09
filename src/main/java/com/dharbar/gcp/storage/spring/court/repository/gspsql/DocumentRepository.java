package com.dharbar.gcp.storage.spring.court.repository.gspsql;

import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {

	@Query("select d from Document d left join fetch d.user")
	Iterable<Document> findAllEagerly();
}
