package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.implementation.CourseServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/listcourses")
public class ListCourses {
    private final CourseServiceImplementation courseServiceImplementation;

    public ListCourses(CourseServiceImplementation courseServiceImplementation) {
        this.courseServiceImplementation = courseServiceImplementation;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {

        List<Course> lista = courseServiceImplementation.listAllCourses();
        model.addAttribute("lista", lista);
        model.addAttribute("bodyContent", "listCourses");
        return "master-template";
    }

    @PostMapping
    public String listCourses(HttpServletRequest request, @RequestParam(required = false) Long courseId) {
        request.getSession().setAttribute("courseId", courseId);
        //String courseId= request.getParameter("courseId");
        return "redirect:/liststudents";
    }
}
//    @PostMapping()
//    public String addStudentPage(@RequestParam(required = false) String courseId,HttpServletRequest request) {
//        request.getSession().setAttribute("courseId",courseId);
//        return "redirect:/students/allStudents";
//    }
//String courseId= req.getParameter("courseId");
//            if(courseId!=null) {
//                    Long Id = Long.valueOf(courseId);
//                    req.getSession().setAttribute("courseId", Id);
//                    resp.sendRedirect("/addStudent");
//                    }
//                    else{
//                    resp.sendRedirect("/listCourses");
//                    }