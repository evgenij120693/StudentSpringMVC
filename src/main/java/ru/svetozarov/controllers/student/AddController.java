package ru.svetozarov.controllers.student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.svetozarov.common.exceptions.UserDaoException;
import ru.svetozarov.models.pojo.Student;
import ru.svetozarov.services.StudentService;

/**
 * Created by Evgenij on 06.03.2017.
 */
@Controller
public class AddController {

    private Logger logger = Logger.getLogger(AddController.class);


    StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(Model model){
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(Model model,
                                @RequestParam(name = "id_group") Integer id_group,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "bith_date") String date,
                                @RequestParam(name = "sex") String sex){
        Student student = new Student(0, name, date, sex, id_group);
        System.out.println("fsdfsdfsdfgsdfgdfs");
        try {
            if (studentService.insertStudent(student)) {
                return "redirect:list";
            } else {
                return "redirect:error";
            }
        } catch (UserDaoException e) {
            logger.error(e);
            return "redirect:error";
        }
    }
}
