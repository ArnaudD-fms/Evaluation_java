package fr.fms.trainingsales.dao;

import fr.fms.trainingsales.model.Training;

import java.util.List;

public interface TrainingDao {

    /**
     * Récupère toutes les formations disponibles.
     *
     * @return la liste des formations
     */
    List<Training> findAll();

    /**
     * Recherche des formations à partir d'un mot-clé ou d'une phrase.
     *
     * @param keyword mot-clé ou phrase recherché dans le nom ou la description
     * @return les formations correspondant à la recherche
     */
    List<Training> findByKeyword(String keyword);

    /**
     * Recherche les formations selon leur mode de réalisation.
     *
     * @param isRemote true pour les formations à distance,
     *                 false pour les formations en présentiel
     * @return les formations correspondant au critère
     */
    List<Training> findByRemote(boolean isRemote);
}
