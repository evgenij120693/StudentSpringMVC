package ru.svetozarov.controllers.student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.Student;
import ru.svetozarov.services.StudentService;

import java.util.List;

/**
 * Created by Evgenij on 06.03.2017.
 */
@Controller
public class ListController  {
    private Logger logger = Logger.getLogger(ListController.class);


    StudentService studentService;
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(name = "/list", method = RequestMethod.GET)
    public String listGet(Model model){
        try {
            List<Student> list = studentService.listShow();
            for (Student st :
                    list) {
                System.out.println(st.getName());
            }
           model.addAttribute("list", list);
            return "list";
        } catch (UserDaoException e) {
            logger.error(e);
            return "redirect:error";
        }
    }


}
