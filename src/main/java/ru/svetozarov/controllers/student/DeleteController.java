package ru.svetozarov.controllers.student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.services.StudentService;

/**
 * Created by Evgenij on 06.03.2017.
 */
@Controller
public class DeleteController {
    private Logger logger = Logger.getLogger(DeleteController.class);

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteGet(@RequestParam(name = "id") Integer id){
        try {
            studentService.deleteStudent(id);
            return "redirect:list";
        } catch (UserDaoException e) {
            logger.error(e);
            return "redirect:error";
        }
    }
}
