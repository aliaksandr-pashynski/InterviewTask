package app;

import app.core.EntityService;
import app.core.PersonService;
import app.entities.Person;

public class BaseTest {

    protected EntityService<Person> personService = new PersonService("api.agify.io");
}
