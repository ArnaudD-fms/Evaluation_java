package fr.fms.trainingsales.test;

import fr.fms.trainingsales.dao.TrainingDaoImpl;
import fr.fms.trainingsales.model.Training;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    }
}
