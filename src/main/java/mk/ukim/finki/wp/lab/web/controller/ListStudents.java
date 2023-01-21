package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.User;
//import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/liststudents")
public class ListStudents {

    private final StudentServiceImplementation studentServiceImplementation;
    private final UserService userService;

    public ListStudents(StudentServiceImplementation studentServiceImplementation, UserService userService) {
        this.studentServiceImplementation = studentServiceImplementation;
        this.userService = userService;
    }

    @GetMapping
    public String listStudents(HttpServletRequest request, Model model){
        Long courseId=(Long) request.getSession().getAttribute("courseId");
        model.addAttribute("courseId", courseId);
        List<Student> studenti=studentServiceImplementation.listAll();
        model.addAttribute("studenti", studenti);
        model.addAttribute("bodyContent", "listStudents");
        return "master-template";
    }

    @PostMapping("/admin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String PostStudents(HttpServletRequest request) {
        String newStudent = request.getParameter("createNew");
        String oceni = request.getParameter("listGrades");
        String usernameSubmit = (String) request.getParameter("size");
//        String studentsInCourse=request.getParameter("liststudentsincourse");
//        if(studentsInCourse != null && ){
//            return "redirect:/courses/listStudentsinCourse";
//        }
        if (oceni != null) {
            return "redirect:/courses/grades";
            //resp.sendRedirect("/courses/grades");
        }
        String username1= request.getRemoteUser();
        User user= (User) userService.loadUserByUsername(username1);
//        if(username1.equals("admin")){
//            if (newStudent != null) {
//                request.getSession().setAttribute("createNew", newStudent);
//                return "redirect:/courses/addStudent";
//            }
//            if (usernameSubmit != null) {
//                request.getSession().setAttribute("usernameSubmit", usernameSubmit);
//                return "redirect:/studentEnrollmentSummary/admin";
//            }
//        }
        if(user.getRole().toString().equals("ROLE_ADMIN")) {
            if (newStudent != null) {
                request.getSession().setAttribute("createNew", newStudent);
                return "redirect:/courses/addStudent";
            }
            if (usernameSubmit != null) {
                request.getSession().setAttribute("usernameSubmit", usernameSubmit);
                return "redirect:/studentEnrollmentSummary/admin";
            }
        }
        return "redirect:/studentEnrollmentSummary";
    }

//    @PostMapping
//    @PreAuthorize("isAnonymous()")
//    public String PostStudentsUser(HttpServletRequest request) {
//        return "redirect:/studentEnrollmentSummary";
//    }
}