package com.dharbar.gcp.storage.spring.court.repository.gspsql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Setter
@Entity

public class User {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String fileName;
}
