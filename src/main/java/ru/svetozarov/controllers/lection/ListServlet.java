package ru.svetozarov.controllers.lection;

import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.controllers.LoginServlet;
import ru.svetozarov.models.pojo.Lection;
import org.apache.log4j.Logger;
import ru.svetozarov.services.LectionService;
import ru.svetozarov.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Шмыга on 23.02.2017.
 */
@WebServlet("/lection/list")
public class ListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Lection> list = LectionService.listShow();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/lection/list.jsp").forward(req, resp);
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
