package com.softilys.university.demo.Repository;

import com.softilys.university.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,String> {

}
