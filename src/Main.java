import fr.fms.trainingsales.business.ConsoleHandler;
import fr.fms.trainingsales.business.TrainingSales;
import fr.fms.trainingsales.config.DatabaseConnection;
import fr.fms.trainingsales.dao.TrainingDao;
import fr.fms.trainingsales.dao.TrainingDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner);

        Connection connection = DatabaseConnection.getConnection();
        TrainingDao trainingDao = new TrainingDaoImpl(connection);

        TrainingSales trainingSales = new TrainingSales(consoleHandler, trainingDao);

        trainingSales.displayTrainingsMenu();

    }
}