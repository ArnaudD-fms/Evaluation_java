package fr.fms.trainingsales.test;

import fr.fms.trainingsales.dao.TrainingDaoImpl;
import fr.fms.trainingsales.model.Training;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TrainingDaoTest {

    private Connection connection;
    private TrainingDaoImpl trainingDao;

    @Before
    public void setUp() throws SQLException {
        String URL = "jdbc:mariadb://localhost:3306/test_training_sales";
        String USER = "admin";
        String PASSWORD = "test";
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        trainingDao = new TrainingDaoImpl(connection);

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM ts_training");
            statement.executeUpdate("ALTER TABLE ts_training AUTO_INCREMENT = 1");

            statement.executeUpdate(
                    "INSERT INTO ts_training (`tr_name`, `tr_description`, `tr_duration`, `tr_remote`, `tr_price`) VALUES " +
                            "('Test1', 'Description du test 1', 10, false, 999.99)," +
                            "('Test2', 'Description du test 2', 20, false, 999.99)," +
                            "('Test3', 'Description du test 3', 30, true, 999.99)," +
                            "('Test4', 'Description du test 4', 40, true, 999.99)"
            );
        }
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void findAllTraining() {
        List<Training> trainings = trainingDao.findAll();

        assertNotNull(trainings);
        assertEquals("Test1", trainings.get(0).getName());
        assertEquals(1, trainings.get(0).getId());
        assertEquals("Description du test 3", trainings.get(2).getDescription());
    }
}
