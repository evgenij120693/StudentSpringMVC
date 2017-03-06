package ru.svetozarov.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.dao.UserDAO;
import ru.svetozarov.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Шмыга on 23.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserService {
    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;



    private int anInt = 0;

    public  User autorize(String login, String password) throws UserDaoException {
        if(anInt == 0) {
            Random random = new Random(100);
            anInt = random.nextInt();
        }
        logger.trace("user service anInt = "+anInt);
        return  userDAO.getUserByLoginAndPassword(login, password);

    }

    public  boolean register(String login, String password) throws UserDaoException {
        if(userDAO.insertUser(login, password)){
            return true;
        }else{
            return false;
        }
    }
}
