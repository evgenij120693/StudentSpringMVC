package ru.svetozarov.models.connector;

import com.mysql.jdbc.Connection;
import ru.svetozarov.common.exceptions.UserDaoException;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class Connector {
    private static Logger logger = Logger.getLogger(Connector.class);

    private static Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/example?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    private final String LOGIN = "root";
    private final String PASSWORD = "1234";

    private Connector() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = (Connection) DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    /**
     * Функция возвращает connection, создает только один connection
     * @return - возвращает ссылку на connection
     * @throws SQLException
     */
    public static Connection getConnection() throws  UserDaoException {
       // if(connection == null) {
        try {
            try {
                new Connector();
            } catch (SQLException e) {
                logger.error(e);
                throw new UserDaoException();
            }
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        //}
        return connection;
    }
}
