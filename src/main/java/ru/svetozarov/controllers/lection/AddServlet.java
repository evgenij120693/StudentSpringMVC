package ru.svetozarov.controllers.lection;

import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.dao.LectionDAO;
import ru.svetozarov.models.pojo.Lection;
import org.apache.log4j.Logger;
import ru.svetozarov.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Шмыга on 23.02.2017.
 */
@WebServlet("/lection/add")
public class AddServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/lection/add.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.trace("onPost");
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        String subject = req.getParameter("subject");

        int id_group = Integer.valueOf(
                (req.getParameter("id_group") != null) ?
                        req.getParameter("id_group") : "0");
        String date = req.getParameter("date");
        Lection lection = new Lection(0, name, text, subject, id_group, date);

        try {
            if (LectionDAO.insertLectionById(lection)) {
                resp.sendRedirect("/students/lection/list");
            } else {
                resp.sendRedirect("/students/error.jsp");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }
}
