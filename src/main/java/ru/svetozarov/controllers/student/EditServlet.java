package ru.svetozarov.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.Student;
import org.apache.log4j.Logger;
import ru.svetozarov.services.StudentService;
import ru.svetozarov.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class EditServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EditServlet.class);

    @Autowired
    private StudentService student_service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("onGet");
        int id = Integer.valueOf(
                (req.getParameter("id") != null) ?
                        req.getParameter("id") : "0");
        if (id != 0) {
            try {
                Student student = student_service.selectStudent(id);
                req.setAttribute("student", student);
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
            } catch (UserDaoException e) {
                logger.trace(e);
                resp.sendRedirect("/student/error.jsp");
            }
        }else{
            logger.trace("add");
            req.getRequestDispatcher("/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("onPost");
        System.out.println("name " + req.getParameter("name"));
        String name = req.getParameter("name");
        String date = req.getParameter("bith_date");
        String sex = req.getParameter("sex");
        int id = Integer.valueOf(
                (req.getParameter("id") != null) ?
                        req.getParameter("id") : "0");
        int id_group = Integer.valueOf(
                (req.getParameter("id_group") != null) ?
                        req.getParameter("id_group") : "0");
        Student student = new Student(id, name, date, sex, id_group);

        try {
            if (student_service.updateStudent(student)) {
                resp.sendRedirect("/students/list");
            } else {
                resp.sendRedirect("/students/error.jsp");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
