package ru.svetozarov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.dao.StudentDAO;
import ru.svetozarov.models.pojo.Student;

import java.util.List;

/**
 * Created by Шмыга on 23.02.2017.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentService {


    private StudentDAO studentDAO;

    @Autowired
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public  List<Student> listShow() throws UserDaoException {
        return studentDAO.selectAllStudent();
    }

    public  Student selectStudent(int id) throws UserDaoException {
        return studentDAO.getStudentById(id);
    }

    public   boolean updateStudent(Student student) throws UserDaoException {
        return studentDAO.updateStudentById(student);
    }

    public  boolean insertStudent(Student student) throws UserDaoException {
        return studentDAO.insertStudentById(student);
    }

    public  boolean deleteStudent(int id) throws UserDaoException {
        return studentDAO.deleteStudent(id);
    }

    public  List<Student>  selectStudentByGroupId(int id) throws UserDaoException {
        return studentDAO.getStudentByGroup(id);
    }
}
