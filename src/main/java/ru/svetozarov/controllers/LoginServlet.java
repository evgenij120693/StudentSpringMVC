package ru.svetozarov.controllers;

import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.svetozarov.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Шмыга on 23.02.2017.
 */
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserService userService;



    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private static Logger logger = Logger.getLogger(LoginServlet.class);



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("login"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.autorize(login, password);

            if (user!=null) {
                HttpSession session = req.getSession();
                session.setAttribute("id", user.getIdUser());
                session.setMaxInactiveInterval(30*60);
                resp.sendRedirect("/students/list");
                logger.trace("Authorization successfull");
            } else {
                // req.getRequestDispatcher("/login.jsp").forward(req, resp);
                logger.trace("Authorization unsuccessful");
                resp.sendRedirect("/students/login");
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/students/error.jsp");
        }

    }
}
