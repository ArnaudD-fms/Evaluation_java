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

    @Override
    public List<Training> findByKeyword(String keyword) {

        String sql = "SELECT * FROM ts_training WHERE tr_name LIKE ? OR tr_description LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchedWord = "%" + keyword + "%";
            statement.setString(1, searchedWord);
            statement.setString(2, searchedWord);

            try (ResultSet resultSet = statement.executeQuery()) {

                List<Training> trainings = new ArrayList<>();

                while (resultSet.next()) {
                    trainings.add(mapResultSetToTraining(resultSet));
                }

                return trainings;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Training> findByRemote(boolean isRemote) {

        String sql = "SELECT * FROM ts_training WHERE tr_remote = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, isRemote);

            try (ResultSet resultSet = statement.executeQuery()) {

                List<Training> trainings = new ArrayList<>();

                while (resultSet.next()) {
                    trainings.add(mapResultSetToTraining(resultSet));
                }

                return trainings;
            }

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
