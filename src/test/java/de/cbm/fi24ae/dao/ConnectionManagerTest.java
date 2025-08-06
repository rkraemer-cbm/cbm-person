package de.cbm.fi24ae.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionManagerTest {

    @Test
    public void testConnection() {
        try (Connection connection = ConnectionManager.getConnection()) {
            assertNotNull(connection, "Connection is null");
            assertFalse(connection.isClosed(), "Connection should be open");
        }
        catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}