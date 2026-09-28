package fr.fms.trainingsales.dao;

import fr.fms.trainingsales.model.Training;

import java.util.List;

public interface TrainingDao {

    List<Training> findAll();

    List<Training> findByKeyword(String keyword);

    List<Training> findByRemote(boolean isRemote);
}
