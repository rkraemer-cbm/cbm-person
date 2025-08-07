package de.cbm.fi24ae.dao;

import de.cbm.fi24ae.domain.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

   @Test
    public void aPersonCanBeCreated() {

        Person person = new Person("Müller-Schmidt", "Angela");

        PersonDAO personDAO = new PersonDAO();
        personDAO.createPerson(person);

        assertTrue(person.getId() > 0);

    }

    @Test
    public void getPersonById() {

       PersonDAO personDAO = new PersonDAO();
       Person person = new Person("Müller-Schmidt", "Angela");

       personDAO.createPerson(person);

       Person loadedPerson = personDAO.getPersonById(person.getId());

       assertNotNull(loadedPerson);
       assertEquals(person.getId(),loadedPerson.getId());
       assertTrue(person.equals(loadedPerson));

    }

    @Test
    public void deletePerson() {

        PersonDAO personDAO = new PersonDAO();
        Person person = new Person("Müller-Schmidt", "Angela");

        personDAO.createPerson(person);
        personDAO.deletePerson(person);

        Person loadedPerson = personDAO.getPersonById(person.getId());
        assertNull(loadedPerson);

    }

    @Test
    public void getAllPersons() {

       int maxPerson = 100;
       PersonDAO personDAO = new PersonDAO();

       for(int i = 0; i < maxPerson; i++) {
           personDAO.createPerson(new Person("meyer" + i, "ursula_" + i));
       }

       List<Person> personen = personDAO.getAll();

       assertNotNull(personen.size() >= maxPerson);

    }

   @AfterAll
    public static void tearDownAll() {
       PersonDAO personDAO = new PersonDAO();
       List<Person> personen = personDAO.getAll();
       for(Person person : personen) {
           personDAO.deletePerson(person);
       }
    }

}