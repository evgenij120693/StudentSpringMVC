package ru.svetozarov.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.dao.LectionDAO;
import ru.svetozarov.models.pojo.Lection;

import java.util.List;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LectionService {

    public static List<Lection> listShow() throws UserDaoException {
        return LectionDAO.selectAllLection();
    }

    public static Lection selectStudent(int id) throws UserDaoException {
        return LectionDAO.getLectionById(id);
    }

    public static  boolean updateLection(Lection lection) throws UserDaoException {
        return LectionDAO.updateLectionById(lection);
    }

    public static boolean insertLection(Lection lection) throws UserDaoException {
        return LectionDAO.insertLectionById(lection);
    }

    public static boolean deleteLection(int id) throws UserDaoException {
        return LectionDAO.deleteLection(id);
    }

    public  List<Lection> getNearedLection() throws UserDaoException {
        return LectionDAO.selectNearsLection();
    }
}
