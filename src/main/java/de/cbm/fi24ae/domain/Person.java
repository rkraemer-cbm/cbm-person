package de.cbm.fi24ae.domain;

import java.util.Objects;

public class Person {

    private int id;

    private String firstName;
    private String lastName;

    public Person(String lastName, String firstName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id > 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.checkNames(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.checkNames(lastName);
        this.lastName = lastName;
    }

    private void checkNames(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        if (name.length() < 5) {
            throw new IllegalArgumentException("Name '" + name + "' ist zu kurz (mindestens 5 Zeichen erforderlich).");
        }
        if (name.length() > 15) {
            throw new IllegalArgumentException("Name '" + name + "' ist zu lang (maximal 15 Zeichen erlaubt).");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
