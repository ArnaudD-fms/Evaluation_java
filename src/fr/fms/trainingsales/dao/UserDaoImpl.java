package fr.fms.trainingsales.dao;

import fr.fms.trainingsales.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByCredentials(String login, String password) {

        String sql = "SELECT * FROM ts_user WHERE us_login = ? AND us_password = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) return mapResultSetToUser(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User(
                rs.getString("us_mail"),
                rs.getString("us_login")
        );
        user.setCompany(rs.getString("us_company"));
        user.setId(rs.getInt("us_id_user"));
        return user;
    }
}
