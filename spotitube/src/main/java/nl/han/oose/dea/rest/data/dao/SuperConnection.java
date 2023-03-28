package nl.han.oose.dea.rest.data.dao;

import jakarta.inject.Named;
import nl.han.oose.dea.rest.data.exceptions.custom.database.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Named("super")
public class SuperConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DeaSQL";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

//    DatabaseProperties databaseProperties = new DatabaseProperties();

    //layer supertype.
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Error connecting to the database.", e);
        }
    }
}
