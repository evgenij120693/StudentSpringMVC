package ru.svetozarov.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.User;
import ru.svetozarov.services.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by Evgenij on 06.03.2017.
 */
@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);


    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Model model,
                            @RequestParam(name = "login") String login,
                            @RequestParam(name = "password") String password){
        try {
            User user = userService.autorize(login, password);

            if (user!=null) {
               /* HttpSession session = req.getSession();
                session.setAttribute("id", user.getIdUser());
                session.setMaxInactiveInterval(30*60);*/
                logger.trace("Authorization successfull");
                return "redirect:list";

            } else {
                // req.getRequestDispatcher("/login.jsp").forward(req, resp);
                logger.trace("Authorization unsuccessful");
               return "login";
            }
        } catch (UserDaoException e) {
            logger.error(e);
           return "redirect:error";
        }
    }

}
