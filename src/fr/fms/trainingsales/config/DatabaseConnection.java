package fr.fms.trainingsales.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL =
            "jdbc:mariadb://localhost:3306/training_sales";

    private static final String USER = "admin";
    private static final String PASSWORD = "unbreakable_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
