package nl.han.oose.dea.rest.data.dao;

import nl.han.oose.dea.rest.data.exceptions.custom.login.UserNotFoundException;

import java.sql.*;

public class LoginDAO extends SuperConnection {

        public String userValidationCheck(String user, String password) {
            //layer supertype.

            try (Connection conn = getConnection()) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                stmt.setString(1, user);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getString("username");
                } else {
                    return null;
                }

            } catch (SQLException e) {
                throw new UserNotFoundException("Error retrieving the user from the database.");
            }
        }

        public void createUser(String user, String password) {
            //layer supertype.

            try (Connection conn = getConnection()) {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                stmt.setString(1, user);
                stmt.setString(2, password);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new UserNotFoundException("Error retrieving the user from the database.");
            }
        }
}
