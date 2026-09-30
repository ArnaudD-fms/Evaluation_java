package fr.fms.trainingsales.test;

import fr.fms.trainingsales.config.TestDatabaseConnection;
import fr.fms.trainingsales.dao.UserDao;
import fr.fms.trainingsales.dao.UserDaoImpl;
import fr.fms.trainingsales.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class UserDaoTest {

    private Connection connection;
    private UserDao userDao;

    @Before
    public void setUp() throws SQLException {
        connection = TestDatabaseConnection.getConnection();
        userDao = new UserDaoImpl(connection);

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM ts_user");
            statement.executeUpdate("ALTER TABLE ts_user AUTO_INCREMENT = 1");

            statement.executeUpdate(
                    "INSERT INTO ts_user (`us_mail`, `us_company`, `us_login`, `us_password`) VALUES " +
                            "('user.test@gmail.com', 'FMS', 'user_test', '1234')"
            );
        }
    }

    @Test
    public void findUserByCredentials() {
        User user = userDao.findByCredentials("user_test", "1234");

        assertNotNull(user);
        assertEquals("user.test@gmail.com", user.getEmail());
    }

    @Test
    public void findUserByCredentialsWithWrongPassword() {
        User user = userDao.findByCredentials("user_test", "5678");

        assertNull(user);
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }
}
