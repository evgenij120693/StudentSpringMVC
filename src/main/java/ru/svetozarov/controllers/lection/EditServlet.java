package ru.svetozarov.controllers.lection;

import ru.svetozarov.common.exceptions.UserDaoException;
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

/**
 * Created by Шмыга on 23.02.2017.
 */
@WebServlet("/lection/edit")
public class EditServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EditServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("onGet");
        int id = Integer.valueOf(
                (req.getParameter("id") != null) ?
                        req.getParameter("id") : "0");
        if (id != 0) {
            try {
                Lection lection = LectionService.selectStudent(id);
                req.setAttribute("lection", lection);
                req.getRequestDispatcher("/lection/edit.jsp").forward(req, resp);
            } catch (UserDaoException e) {
                logger.trace(e);
                resp.sendRedirect("/students/error.jsp");
            }
        }else{
            logger.trace("add");
           // req.getRequestDispatcher("/lection/edit.jsp").forward(req, resp);
            resp.sendRedirect("/students/lection/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("onPost");
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        String subject = req.getParameter("subject");
        int id = Integer.valueOf(
                (req.getParameter("id") != null) ?
                        req.getParameter("id") : "0");
        int id_group = Integer.valueOf(
                (req.getParameter("id_group") != null) ?
                        req.getParameter("id_group") : "0");
        String date = req.getParameter("date");
        Lection lection = new Lection(id, name, text, subject, id_group, date);

        try {
            if (LectionService.updateLection(lection)) {
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
