package fr.fms.trainingsales.business;

import fr.fms.trainingsales.dao.TrainingDao;
import fr.fms.trainingsales.model.Training;

import java.util.ArrayList;
import java.util.List;

public class TrainingSales {

    private final ConsoleHandler console;
    private final TrainingDao trainingDao;

    public TrainingSales(ConsoleHandler console, TrainingDao trainingDao) {
        this.console = console;
        this.trainingDao = trainingDao;
    }

    public void displayTrainingsMenu() {
        System.out.println("1 - Afficher toutes les formations");
        System.out.println("2 - Rechercher les formations par mot clé");
        System.out.println("3 - Rechercher les formations par modalité (présentiel ou distanciel)");

        int userChoise = console.getUserChoice(3);

        List<Training> trainings = new ArrayList<>();

        switch (userChoise) {
            case 1:
                trainings = trainingDao.findAll();
                break;
            case 2:
                String keyword = console.getKeyword();
                trainings = trainingDao.findByKeyword(keyword);
                break;
            case 3:
                boolean isRemote = askForRemote();
                trainings = trainingDao.findByRemote(isRemote);
        }

        for (Training training : trainings) {
            System.out.println(training.getName());
        }
    }

    private boolean askForRemote() {
        System.out.println("1 - Afficher les formations en présentiel");
        System.out.println("2 - Afficher les formations en distanciel");

        int userChoise = console.getUserChoice(2);

        return userChoise == 2;
    }
}
