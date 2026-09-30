package fr.fms.trainingsales.dao;

import fr.fms.trainingsales.model.User;

public interface UserDao {

    /**
     * Récupère l'utilisateur correspondant au login / password fourni
     *
     * @return un utilisateur
     */
    User findByCredentials(String login, String password);
}
