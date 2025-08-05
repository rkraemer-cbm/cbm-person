package de.cbm.fi24ae.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void aPersonCanBeCreated() {

        Person person = new Person("Meyer", "Uschi");
        assertEquals("Meyer", person.getLastName());
        assertEquals("Uschi", person.getFirstName());

        person.setId(1);
        assertEquals(1, person.getId());

    }

    @Test
    public void anIdCanBotSetTwice() {
        Person person = new Person("Meyer", "Uschi");
        person.setId(5);
        assertThrows(IllegalArgumentException.class, () -> person.setId(5));
    }

    @Test
    public void aNameMustHaveACertainLength() {

        // name < 5
        assertThrows(IllegalArgumentException.class, () -> new Person("Mey", "Us"));

        // name > 15
        assertThrows(IllegalArgumentException.class, () -> new Person("Meyererererererererereeeeee", "Ussssssssssssssssssssssss"));

        // empty
        assertThrows(IllegalArgumentException.class, () -> new Person("", "Ussss"));

        // null
        assertThrows(IllegalArgumentException.class, () -> new Person(null, "Usssss"));
    }

    @Test
    public void aPersonIsEqual() {

        Person personA = new Person("Meyer", "Uschi");
        Person personB = new Person("Meyer", "Uschi");

        assertFalse(personA == personB);
        assertTrue(personA.equals(personB));

    }

    @Test
    public void aPersonIsNotEqual() {

        Person personA = new Person("Meyer", "Uschi");
        Person personB = new Person("Meyer", "Frauke");

        assertFalse(personA == personB);
        assertFalse(personA.equals(personB));

    }

}