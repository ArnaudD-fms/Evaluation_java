package fr.fms.trainingsales.business;

import fr.fms.trainingsales.dao.TrainingDao;

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

        switch (userChoise) {
            case 1:
                trainingDao.findAll();
                break;
            case 2:
                //trainingDao.findByKeyword()
                break;
            case 3:
                //trainingDao.findByRemote()
        }
    }
}
