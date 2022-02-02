package com.example.common;

import com.example.services.EntityService;
import com.example.services.PersonService;
import com.example.entities.Person;

public class BaseTest {

    protected EntityService<Person> personService = new PersonService("api.agify.io");
}
