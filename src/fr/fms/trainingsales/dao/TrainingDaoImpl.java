package fr.fms.trainingsales.dao;

import fr.fms.trainingsales.config.DatabaseConnection;
import fr.fms.trainingsales.model.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingDaoImpl implements TrainingDao{

    private final Connection connection;

    public TrainingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Training> findAll() {

        String sql = "SELECT * FROM ts_training";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            List<Training> trainings = new ArrayList<>();

            while (resultSet.next()) {
                trainings.add(mapResultSetToTraining(resultSet));
            }

            return trainings;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private static Training mapResultSetToTraining(ResultSet rs) throws SQLException {
        Training training = new Training(
                rs.getString("tr_name"),
                rs.getString("tr_description"),
                rs.getInt("tr_duration"),
                rs.getBoolean("tr_remote"),
                rs.getBigDecimal("tr_price")
        );
        training.setId(rs.getInt("tr_id_training"));
        return training;
    }
}
