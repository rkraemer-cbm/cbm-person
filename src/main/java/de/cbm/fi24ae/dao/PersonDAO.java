package de.cbm.fi24ae.dao;

import de.cbm.fi24ae.domain.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonDAO {

    private static final String SQL_DELETE = "delete from rk_person where id =?";
    private static final String SQL_GET_BY_ID = "select * from rk_person where id = ?";
    private static final String SQL_ALL = "select id, first_name, last_name from rk_person";
    private static final String SQL_INSERT = "insert into rk_person (last_name, first_name) values (?,?)";
    private static final String SQL_UPDATE = "update person set first_name = ? and last_name = ? where id = ?";

    public Person getPersonById(long id) {

        Person person = null;

        try (

                Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID)) {

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                person = new Person(
                        rs.getString("last_name"),
                        rs.getString("first_name")
                );
                person.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        return person;

    }

    public void deletePerson(Person person) {

        if (person.getId() == 0) {
            throw new IllegalArgumentException("person id must not be null");
        }

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

            statement.setLong(1, person.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new IllegalArgumentException("person id " + person.getId() + " does not exist");
            }

        }
        catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }

    }

    public List<Person> getAll() {

        List<Person> persons = new LinkedList<>();

        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ALL);
            ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Person person = new Person(
                        rs.getString("last_name"),
                        rs.getString("first_name"));
                person.setId(rs.getInt("id"));
                persons.add(person);

            }
        }
        catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        return persons;

    }

    public void createPerson(Person person) {

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        person.setId(generatedKeys.getInt(1));
                    }
                }
            }

        }
        catch (SQLException e) {
            throw new DataAccessException("Fehler beim Speichern der Person", e);
        }

    }

}

class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
