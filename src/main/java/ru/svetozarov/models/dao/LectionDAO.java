package ru.svetozarov.models.dao;

import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Lection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 24.02.2017.
 */
public class LectionDAO {
    private static Logger logger = Logger.getLogger(LectionDAO.class);
    private static final String QUERY_SELECT_ALL = "select * from example.lection";
    private static final String QUERY_SELECT_NEARED = "select * from example.lection where date>? and date<?";
    private static final String QUERY_SELECT_BY_ID = "select * from example.lection where id=?";
    private static final String QUERY_UPDATE_BY_ID = "update example.lection set name =?," +
            " text=?, subject=?, id_group=?, date=? where id=?";
    private static final String QUERY_INSERT_STUDENT = "insert into example.lection (name, text, subject, id_group, date )" +
            "VALUES (?,?,?,?,?)";
    private static final String QUERY_DELETE_BY_ID = "delete from example.lection where id=?";

    public static List<Lection> selectAllLection() throws UserDaoException {
        List<Lection> list = new ArrayList<>();

        try (Connection conn = Connector.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL);
            while (resultSet.next()){
                logger.trace("result select " + resultSet.getString("name"));
                Lection lection =new Lection(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("subject"),
                        resultSet.getInt("id_group"),
                        resultSet.getTimestamp("date").toString()
                );
                list.add(lection);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return list;
    }

    public static Lection getLectionById(int id) throws UserDaoException {
        Lection lection= null;
        try {
            try(Connection conn = Connector.getConnection();
                PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_BY_ID)){
                prepS.setInt(1, id);
                ResultSet res = prepS.executeQuery();
                if(res.next()){
                    lection = new Lection();
                    logger.trace(res.getString("name"));
                    lection.setId(res.getInt("id"));
                    lection.setName(res.getString("name"));
                    lection.setText(res.getString("text"));
                    lection.setSubject(res.getString("subject"));
                    lection.setIdGroup(res.getInt("id_group"));
                    lection.setDate(res.getString("date"));
                }else{
                    logger.trace("select lection by id = "+id+" not found");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return  lection;
    }

    public static boolean updateLectionById(Lection lection) throws UserDaoException {
        try {
            try (Connection conn = Connector.getConnection();
                 PreparedStatement prepS = conn.prepareStatement(QUERY_UPDATE_BY_ID)){
                logger.trace(lection.getName());
                prepS.setString(1, lection.getName());
                prepS.setString(2, lection.getText());
                prepS.setString(3, lection.getSubject());
                prepS.setInt(4, lection.getIdGroup());
                prepS.setString(5, lection.getDate());
                prepS.setInt(6,lection.getId());
                int count = prepS.executeUpdate();
                if(count > 0 ){
                    logger.trace("update lection id="+lection.getId()+" successfull" );
                    return true;
                }else{
                    logger.trace("update lection id="+lection.getId()+" failed");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public static boolean insertLectionById(Lection lection) throws UserDaoException {
        try {
            try (Connection conn = Connector.getConnection();
                 PreparedStatement prepS = conn.prepareStatement(QUERY_INSERT_STUDENT)){
                logger.trace(lection.getName());
                prepS.setString(1, lection.getName());
                prepS.setString(2, lection.getText());
                prepS.setString(3, lection.getSubject());
                prepS.setInt(4, lection.getIdGroup());
                prepS.setString(5, lection.getDate());
                int count = prepS.executeUpdate();
                if(count > 0 ){
                    logger.trace("insert lection id="+lection.getName()+" successfull" );
                    return true;
                }else{
                    logger.trace("insert lection id="+lection.getName()+" failed");
                }

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public static boolean deleteLection(int id) throws UserDaoException {
        try {
            try(Connection conn = Connector.getConnection();
                PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_BY_ID)) {
                statement.setInt(1,id);
                int count = statement.executeUpdate();
                if(count > 0 ){
                    logger.trace("delete lection id="+id+" successfull" );
                    return true;
                }else{
                    logger.trace("delete lection id="+id+" failed");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public static List<Lection> selectNearsLection() throws UserDaoException {
        List<Lection> list = new ArrayList<>();

        try (Connection conn = Connector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(QUERY_SELECT_NEARED);
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()+60*60*1000));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                logger.trace("result select " + resultSet.getString("name"));
                Lection lection =new Lection(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("subject"),
                        resultSet.getInt("id_group"),
                        resultSet.getTimestamp("date").toString()
                );
                list.add(lection);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return list;
    }

}
