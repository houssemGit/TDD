package com.softilys.university.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties("hibernateLazyInitializer")
@Table(name="person")
public class Person implements Serializable {
    @Id
    private String email;
    private String lastName;
    private String firstName;

    public Person() {

    }

    public Person(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }
}
