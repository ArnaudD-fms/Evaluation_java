import fr.fms.trainingsales.business.ConsoleHandler;
import fr.fms.trainingsales.business.TrainingSales;
import fr.fms.trainingsales.config.DatabaseConnection;
import fr.fms.trainingsales.dao.TrainingDao;
import fr.fms.trainingsales.dao.TrainingDaoImpl;
import fr.fms.trainingsales.dao.UserDao;
import fr.fms.trainingsales.dao.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner);

        Connection connection = DatabaseConnection.getConnection();
        TrainingDao trainingDao = new TrainingDaoImpl(connection);
        UserDao userDao = new UserDaoImpl(connection);

        TrainingSales trainingSales = new TrainingSales(consoleHandler, trainingDao, userDao);

        boolean proceed = true;
        while(proceed) {
            proceed = trainingSales.displayTrainingsMenu();
        }

        scanner.close();
        connection.close();

    }
}