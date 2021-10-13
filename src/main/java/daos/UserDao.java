package daos;


import entities.Admin;
import entities.Patient;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Optional;

public class UserDao extends DaoAbstract<User>{

    @Override
    public int save(User entity) {
        int id=0;
        final String QUERY= "INSERT INTO users (username, password, role) VALUES(?, ?, ?)";
        try(PreparedStatement preparedStatement =
                    idReturnStatementForVarArgs(QUERY, entity.getUserName() , entity.getPassword() , entity.getRole().toString())) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getInt(1);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public Optional<User> getByUsername(String username) {
        Optional<User> userOptional=Optional.empty();
        final String QUERY= "SELECT id, password, role FROM users WHERE username = ? ";
        try(PreparedStatement preparedStatement =
                    statementForVarArgs(QUERY, username);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String password = resultSet.getString(2);
                String role = resultSet.getString(3).toUpperCase();
                if (role.equals("admin")) {
                    userOptional = Optional.of(new Admin(id, username, password, User.Role.valueOf(role)));
                }else{
                    userOptional = Optional.of(new Patient(id, username, password, User.Role.valueOf(role)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOptional;
    }

}
