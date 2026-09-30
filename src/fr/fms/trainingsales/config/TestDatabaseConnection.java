package fr.fms.trainingsales.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe permettant de se connecter à la BDD de test
 */
public class TestDatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/test_training_sales";
    private static final String USER = "admin_test";
    private static final String PASSWORD = "test";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
