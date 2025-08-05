package de.cbm.fi24ae.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void aPersonCanBeCreated() {
        Person person = new Person("Meyer", "Uschi");
        assertEquals("Meyer", person.getLastName());
    }

    @Test
    public void anIdCanBotSetTwice() {
        Person person = new Person("Meyer", "Uschi");
        person.setId(5);
        assertThrows(IllegalArgumentException.class, () -> person.setId(5));
    }

}