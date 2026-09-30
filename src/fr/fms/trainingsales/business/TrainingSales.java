package fr.fms.trainingsales.business;

import fr.fms.trainingsales.dao.TrainingDao;
import fr.fms.trainingsales.dao.UserDao;
import fr.fms.trainingsales.model.Credentials;
import fr.fms.trainingsales.model.Training;
import fr.fms.trainingsales.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'afficher en console les actions que l'utilisateur peut réaliser, ainsi que
 * d'appeler les méthodes de DAO correspondantes.
 */
public class TrainingSales {

    private final ConsoleHandler console;
    private final TrainingDao trainingDao;
    private final UserDao userDao;

    private User user;

    public TrainingSales(ConsoleHandler console, TrainingDao trainingDao, UserDao userDao) {
        this.console = console;
        this.trainingDao = trainingDao;
        this.userDao = userDao;
    }

    /**
     * Affiche le menu principale et demande à l'utilisateur quelle action il souhaite effectuer.
     *
     * @return un booléen indiquant si l'application doit de poursuivre ou non
     */
    public boolean displayTrainingsMenu() {

        // --- Affichage du menu ---

        // Si un utilisateur est connecté le login de l'utilisateur est affiché
        if (user != null) System.out.println("Bienvenue " + user.getLogin());

        System.out.println("1 - Afficher toutes les formations");
        System.out.println("2 - Rechercher les formations par mot clé");
        System.out.println("3 - Rechercher les formations par modalité (présentiel ou distanciel)");

        // affichage d'une option différente si un utilisateur est connecté ou non
        if (user != null) System.out.println("4 - se déconnecter");
        else System.out.println("4 - se connecter");

        System.out.println("5 - Quitter l'application");

        // Récupération du choix de l'utilisateur
        int userChoise = console.getUserChoice(5);

        List<Training> trainings = new ArrayList<>();

        switch (userChoise) {
            case 1:
                // Récupération de toutes les formations
                trainings = trainingDao.findAll();
                break;

            case 2:
                // Récupération des formations par mot-clé
                System.out.println("Saisissez un mot clé ou une phrase que vous souhaitez rechercher : ");
                String keyword = console.getValidString();
                trainings = trainingDao.findByKeyword(keyword);
                break;

            case 3:
                // Récupération des formations présentielles ou distancielles
                boolean isRemote = askForRemote();
                trainings = trainingDao.findByRemote(isRemote);
                break;

            case 4:
                // Si un user est connecté alors on le déconnecte
                if (user != null) {
                    user = null;
                    break;
                }

                // Sinon on demande à l'utilisateur de rentrer ses informations de connection.
                Credentials credentials = askForCredentials();
                User user = userDao.findByCredentials(credentials.getLogin(), credentials.getPassword());

                // Si les informations de connection ne sont pas bonne ou retourne au menu
                // sinon on connecte l'utilisateur
                if (user == null) System.out.println("Identifisants de connection incorrects");
                else this.user = user;
                break;

            case 5:
                return false;
        }

        for (Training training : trainings) {
            System.out.println(training + "\n");
        }

        return true;
    }

    /**
     * Demande à l'utilisateur de choisir s'il souhaite afficher les formations en présentiel ou distanciel.
     *
     * @return un booléen indiquant si l'utilisateur souhaite afficher les formations à distance
     */
    private boolean askForRemote() {
        System.out.println("1 - Afficher les formations en présentiel");
        System.out.println("2 - Afficher les formations en distanciel");

        int userChoice = console.getUserChoice(2);

        return userChoice == 2;
    }

    /**
     * Demande à l'utilisateur de rentrer ses informations de connection.
     *
     * @return un objet Credentials contenant le login et le mot de passe de l'utilisateur
     */
    private Credentials askForCredentials() {

        String login = null;
        while(login == null) {
            System.out.println("Veuillez saisir votre nom d'utilisateur : ");
            login = console.getValidString();
        }

        String password = null;
        while(password == null) {
            System.out.println("Veuillez saisir votre mot de passe : ");
            password = console.getValidString();
        }

        return new Credentials(login, password);
    }
}
