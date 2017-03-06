package ru.svetozarov.controllers.student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.Student;
import ru.svetozarov.services.StudentService;

/**
 * Created by Evgenij on 06.03.2017.
 */
@Controller
public class EditController  {
    private Logger logger = Logger.getLogger(EditController.class);


    private StudentService student_service;

    @Autowired
    public void setStudent_service(StudentService student_service) {
        this.student_service = student_service;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGet(Model model,
                          @RequestParam(name = "id", defaultValue = "0") Integer id){
        if (id != 0) {
            try {
                Student student = student_service.selectStudent(id);
                model.addAttribute("student", student);
                return "edit";
            } catch (UserDaoException e) {
                logger.trace(e);
                return "redirect:error";
            }
        }else{
            logger.trace("add");
            return "edit";
        }
    }
}
