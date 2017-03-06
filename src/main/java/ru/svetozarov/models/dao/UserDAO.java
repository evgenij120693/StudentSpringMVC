package ru.svetozarov.models.dao;

import org.springframework.stereotype.Component;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Шмыга on 23.02.2017.
 */
@Component
public class UserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class);
    private  final String SQL_FIND_USER = "Select * from example.user where login = ? and password = ?";
    private  final String SQL_USER_BY_ID = "Select * from example.user where id = ?";
    private  final String SQL_INSERT_USER = "Insert into example.user (login, password, role)" +
            " values (?, ?, 'user')";

    public  User getUserByLoginAndPassword(String login, String password)
            throws UserDaoException {
        User user = null;

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_FIND_USER)) {
            prepS.setString(1, login);
            prepS.setString(2, password);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setIdUser(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
            }else{
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDaoException();
        }
        return user;
    }

    public  boolean insertUser(String login, String password) throws UserDaoException {

        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_INSERT_USER)) {

            prepS.setString(1, login);
            prepS.setString(2, password);
            int countIsert = prepS.executeUpdate();
            if(countIsert > 0){
                logger.debug("Inserted "+countIsert);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public  User getUserById(int id)
            throws UserDaoException {
        User user = null;
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_USER_BY_ID)) {
            prepS.setInt(1, id);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                logger.trace("Get by id user where id="+id);
                user = new User();
                user.setIdUser(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
            }else{
                logger.debug(id +" not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDaoException();
        }
        return user;
    }


}
