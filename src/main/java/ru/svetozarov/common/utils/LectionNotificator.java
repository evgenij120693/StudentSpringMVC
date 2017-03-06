package ru.svetozarov.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.Lection;
import ru.svetozarov.models.pojo.Student;
import org.apache.log4j.Logger;
import ru.svetozarov.services.StudentService;

import java.util.List;

/**
 * Created by Шмыга on 24.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LectionNotificator {
    private static Logger logger = Logger.getLogger(LectionNotificator.class);

  /*  @Autowired
    StudentService studentService;*/

    public    void notifiByLection(Lection lection) throws UserDaoException {
       /* List<Student> list = new StudentService().selectStudentByGroupId(lection.getId());
        logger.trace(list.size());
        for (Student student :
                list) {
            sendMail.sendMail(student.getEmail(),"Авторизация", "Вы вошли как ");
        }*/
    }
}
