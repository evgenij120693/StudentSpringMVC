package ru.svetozarov.models.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.connector.Connector;
import ru.svetozarov.models.pojo.Student;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шмыга on 23.02.2017.
 */
@Component
public class StudentDAO {

    private static Logger logger = Logger.getLogger(StudentDAO.class);
    private  final String QUERY_SELECT_ALL = "select * from example.student";
    private  final String QUERY_SELECT_BY_ID = "select * from example.student where id=?";
    private  final String QUERY_SELECT_BY_GROUP = "select * from example.student where group_id=?";
    private  final String QUERY_UPDATE_BY_ID = "update example.student set name =?," +
            " bith_date=?, sex=?, group_id=? where id=?";
    private static final String QUERY_INSERT_STUDENT = "insert into example.student (name, bith_date, sex, group_id)" +
            "VALUES (?,?,?,?)";
    private static final String QUERY_DELETE_BY_ID = "delete from example.student where id=?";

    public  List<Student> selectAllStudent() throws UserDaoException {
        List<Student> list =null;
        SqlSessionFactory sqlSessionFactory;
        StudentsMapper studentsMaper;
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("/META-INF/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            studentsMaper = sqlSessionFactory.openSession().getMapper(StudentsMapper.class);
            list = studentsMaper.selectAllStudent();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        try (Connection conn = Connector.getConnection()) {
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL);
//            while (resultSet.next()){
//                logger.trace("result select " + resultSet.getString("name"));
//                Student student =new Student(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("bith_date"),
//                        resultSet.getString("sex"),
//                        resultSet.getInt("group_id")
//                );
//                list.add(student);
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//            throw new UserDaoException();
//        }
        return list;
    }

    public  Student getStudentById(int id) throws UserDaoException {
        SqlSessionFactory sqlSessionFactory;
        StudentsMapper studentsMaper;
        Reader reader = null;
        Student student = null;
        try {
            reader = Resources.getResourceAsReader("/META-INF/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            studentsMaper = sqlSessionFactory.openSession().getMapper(StudentsMapper.class);
            student = studentsMaper.getStudentById(id);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return student;
       /* try {
            try(Connection conn = Connector.getConnection();
            PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_BY_ID)){
                prepS.setInt(1, id);
                ResultSet res = prepS.executeQuery();
                if(res.next()){
                    student = new Student();
                    logger.trace(res.getString("name"));
                    student.setId(res.getInt("id"));
                    student.setName(res.getString("name"));
                    student.setBirth_date(res.getString("bith_date"));
                    student.setSex(res.getString("sex"));
                    student.setIdGroup(res.getInt("group_id"));
                }else{
                    logger.trace("select by id = "+id+" not found");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }*/
    }

    public  boolean updateStudentById(Student student) throws UserDaoException {
        try {
            try (Connection conn = Connector.getConnection();
            PreparedStatement prepS = conn.prepareStatement(QUERY_UPDATE_BY_ID)){
                logger.trace(student.getName());
                prepS.setString(1, student.getName());
                prepS.setString(2, student.getBirth_date());
                prepS.setString(3, student.getSex());
                prepS.setInt(4, student.getIdGroup());
                prepS.setInt(5,student.getId());
                int count = prepS.executeUpdate();
                if(count > 0 ){
                    logger.trace("update id="+student.getId()+" successfull" );
                    return true;
                }else{
                    logger.trace("update id="+student.getId()+" failed");
                }

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public  boolean insertStudentById(Student student) throws UserDaoException {
        try {
            try (Connection conn = Connector.getConnection();
                 PreparedStatement prepS = conn.prepareStatement(QUERY_INSERT_STUDENT)){
                logger.trace(student.getName());
                prepS.setString(1, student.getName());
                prepS.setString(2, student.getBirth_date());
                prepS.setString(3, student.getSex());
                prepS.setInt(4, student.getIdGroup());
                int count = prepS.executeUpdate();
                if(count > 0 ){
                    logger.trace("insert id="+student.getName()+" successfull" );
                    return true;
                }else{
                    logger.trace("insert id="+student.getName()+" failed");
                }

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public  boolean deleteStudent(int id) throws UserDaoException {
        try {
            try(Connection conn = Connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_BY_ID)) {
                statement.setInt(1,id);
                int count = statement.executeUpdate();
                if(count > 0 ){
                    logger.trace("insert id="+id+" successfull" );
                    return true;
                }else{
                    logger.trace("insert id="+id+" failed");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }


    public  List<Student> getStudentByGroup(int id) throws UserDaoException {
        List<Student> list = new ArrayList<>();
        try {
            try(Connection conn = Connector.getConnection();
                PreparedStatement prepS = conn.prepareStatement(QUERY_SELECT_BY_GROUP)){
                prepS.setInt(1, id);
                ResultSet res = prepS.executeQuery();
                while(res.next()){
                    Student student = new Student();
                    logger.trace(res.getString("name"));
                    student.setId(res.getInt("id"));
                    student.setName(res.getString("name"));
                    student.setBirth_date(res.getString("bith_date"));
                    student.setSex(res.getString("sex"));
                    student.setIdGroup(res.getInt("group_id"));
                    student.setEmail(res.getString("email"));
                    list.add(student);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return  list;
    }
}
