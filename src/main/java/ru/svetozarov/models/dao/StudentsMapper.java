package ru.svetozarov.models.dao;

import ru.svetozarov.models.pojo.Student;

import java.util.List;

/**
 * Created by Evgenij on 17.03.2017.
 */
public interface StudentsMapper {
    Student getStudentById(Integer id);
    List<Student> selectAllStudent();
}
