package de.cbm.fi24ae.dao;

import de.cbm.fi24ae.domain.Person;

import java.sql.*;

public class PersonDAO {

    private static final String SQL_INSERT = "insert into rk_person (last_name, first_name) values (?,?)";

    public void createPerson(Person person) {

        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS)) {

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
            throw new DataAccessException("Fehler beim Speichern des Persons", e);
        }

    }

}

class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
