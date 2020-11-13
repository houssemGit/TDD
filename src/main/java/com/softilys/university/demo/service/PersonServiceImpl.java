package com.softilys.university.demo.service;

import com.softilys.university.demo.model.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonServiceImpl {
    Person getPersonDetails(String anyString);
}
