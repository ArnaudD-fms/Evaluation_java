package fr.fms.trainingsales.business;

import fr.fms.trainingsales.dao.TrainingDao;
import fr.fms.trainingsales.model.Training;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'afficher en console les actions que l'utilisateur peut réaliser, ainsi que
 * d'appeler les méthodes de DAO correspondantes.
 */
public class TrainingSales {

    private final ConsoleHandler console;
    private final TrainingDao trainingDao;

    public TrainingSales(ConsoleHandler console, TrainingDao trainingDao) {
        this.console = console;
        this.trainingDao = trainingDao;
    }

    /**
     * Demande à l'utilisateur de choisir quel formations il souhaite afficher en console.
     */
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
                System.out.println("Saisissez un mot clé ou une phrase que vous souhaitez rechercher : ");
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

    /**
     * Demande à l'utilisateur de choisir s'il souhaite afficher les formations en présentiel ou distanciel.
     *
     * @return un booléen indiquant si l'utilisateur souhaite afficher les formations à distance
     */
    private boolean askForRemote() {
        System.out.println("1 - Afficher les formations en présentiel");
        System.out.println("2 - Afficher les formations en distanciel");

        int userChoise = console.getUserChoice(2);

        return userChoise == 2;
    }
}
