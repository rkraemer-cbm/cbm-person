package de.cbm.fi24ae.dao;

import de.cbm.fi24ae.domain.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

   @Test
    public void aPersonCanBeCreated() {

        Person person = new Person("Müller-Schmidt", "Angela");

        PersonDAO personDAO = new PersonDAO();
        personDAO.createPerson(person);

        assertTrue(person.getId() > 0);

    }

}