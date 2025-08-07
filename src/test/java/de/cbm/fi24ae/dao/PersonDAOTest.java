package de.cbm.fi24ae.dao;

import de.cbm.fi24ae.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private PersonDAO personDAO;

    @BeforeEach
    void setup() {
        personDAO = new PersonDAO();
        personDAO.getAll().forEach(personDAO::deletePerson);
    }

    @Test
    void aPersonCanBeCreated() {
        Person person = new Person("Müller-Schmidt", "Angela");
        personDAO.createPerson(person);
        assertTrue(person.getId() > 0);
    }

    @Test
    void updatePerson() {

        Person person = new Person("Müller-Schmidt", "Angela");
        personDAO.createPerson(person);

        person.setFirstName("Neuer Name");
        person.setLastName("Neue Nachmname");

        personDAO.updatePerson(person);

        Person loadedPerson = personDAO.getPersonById(person.getId());
        assertEquals(loadedPerson, person);

    }

    @Test
    void getPersonById() {

       Person person = new Person("Müller-Schmidt", "Angela");
       personDAO.createPerson(person);

       Person loadedPerson = personDAO.getPersonById(person.getId());

       assertNotNull(loadedPerson);
       assertEquals(person.getId(),loadedPerson.getId());
       assertEquals(person, loadedPerson);

    }

    @Test
    void deletePerson() {

        Person person = new Person("Müller-Schmidt", "Angela");

        personDAO.createPerson(person);
        personDAO.deletePerson(person);

        Person loadedPerson = personDAO.getPersonById(person.getId());
        assertNull(loadedPerson);

    }

    @Test
    void getAllPersons() {

       int maxPerson = 100;
       PersonDAO personDAO = new PersonDAO();

       for(int i = 0; i < maxPerson; i++) {
           personDAO.createPerson(new Person("meyer" + i, "ursula_" + i));
       }

       List<Person> personen = personDAO.getAll();

       assertNotNull(personen);
       assertEquals(maxPerson,personen.size());

    }

}