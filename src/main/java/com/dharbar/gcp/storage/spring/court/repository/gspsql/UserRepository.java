package com.dharbar.gcp.storage.spring.court.repository.gspsql;

import com.dharbar.gcp.storage.spring.court.repository.gspsql.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
