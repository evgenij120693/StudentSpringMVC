package ru.svetozarov.models.dao;


import org.hibernate.Criteria;
import org.springframework.stereotype.Component;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.User;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Шмыга on 23.02.2017.
 */
@Component
public class UserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class);
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("STUDENTS");
    private  final String SQL_FIND_USER = "Select * from example.user where login = ? and password = ?";
    private  final String SQL_USER_BY_ID = "Select * from example.user where id = ?";
    private  final String SQL_USER_BY_LOGIN = "Select * from example.user where login = ?";
    private  final String SQL_INSERT_USER = "Insert into example.user (login, password, role)" +
            " values (?, ?, 'user')";


    public User getUserByLogin2(String username) throws UserDaoException {
        System.out.println("LOGInLOGIN LOGIN");
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("login"), username)
                )
        );
        List<User> users = em.createQuery(criteriaQuery).getResultList();
        return users.get(0);
        /*em.getTransaction().begin();
        User user = em.createQuery("select u from User  u" +
                "where login = :login", User.class)
                .setParameter("login", username)
                .getSingleResult();
        em.getTransaction().commit();
        em.close();
        return user;*/
    }

    public  User getUserByLoginAndPassword(String login, String password)
            throws UserDaoException {
        User user = null;
        EntityManager em = FACTORY.createEntityManager();
        em.getTransaction().begin();
       /* user = em.createQuery("select * from user u " +
                "where login = :login and password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();*/


        //user = em.createQuery("select * ffrom user u where login ='"+login+"' ")
//        try (Connection conn = Connector.getConnection();
//             PreparedStatement prepS = conn.prepareStatement(SQL_FIND_USER)) {
//            prepS.setString(1, login);
//            prepS.setString(2, password);
//            ResultSet resultSet = prepS.executeQuery();
//            if (resultSet.next()) {
//                user = new User();
//                user.setIdUser(resultSet.getInt("id"));
//                user.setLogin(resultSet.getString("login"));
//                user.setPassword(resultSet.getString("password"));
//                user.setRole(resultSet.getString("role"));
//                user.setEmail(resultSet.getString("email"));
//            }else{
//                logger.debug(login + " " + password + " not found");
//            }
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//            throw new UserDaoException();
//        }
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

    public User getUserByLogin(String login) throws UserDaoException {
        User user = null;
        try (Connection conn = Connector.getConnection();
             PreparedStatement prepS = conn.prepareStatement(SQL_USER_BY_LOGIN)) {
            prepS.setString(1, login);
            ResultSet resultSet = prepS.executeQuery();
            if (resultSet.next()) {
                logger.trace("Get by login user where id="+login);
                user = new User();
                user.setIdUser(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
            }else{
                logger.debug(login +" not found");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UserDaoException();
        }
        return user;
    }


}
