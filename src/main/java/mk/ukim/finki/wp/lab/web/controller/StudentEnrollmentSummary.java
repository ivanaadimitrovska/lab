package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.implementation.CourseServiceImplementation;
//import mk.ukim.finki.wp.lab.service.implementation.UserServiceImplementation;
import mk.ukim.finki.wp.lab.service.implementation.UserServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/studentEnrollmentSummary")
public class StudentEnrollmentSummary {

    private final CourseServiceImplementation courseServiceImplementation;

//    public StudentEnrollmentSummary(CourseServiceImplementation courseServiceImplementation) {
//        this.courseServiceImplementation = courseServiceImplementation;
//    }
    private final UserServiceImplementation userServiceImplementation;

    public StudentEnrollmentSummary(CourseServiceImplementation courseServiceImplementation, UserServiceImplementation userServiceImplementation) {
        this.courseServiceImplementation = courseServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String studentEnrollmentSummary(HttpServletRequest request, Model model) {
        Long courseId= (Long) request.getSession().getAttribute("courseId");
        model.addAttribute("ime_kurs", courseServiceImplementation.listAllCourses().stream().filter(r -> r.getCourseId().equals(courseId)).findFirst().get().getName());
        String username= (String) request.getSession().getAttribute("usernameSubmit");
//        String username1=request.getRemoteUser();
//        User user= (User) userServiceImplementation.loadUserByUsername(username1);
//        if(user.getRole().toString().equals("ROLE_ADMIN")){
//            courseServiceImplementation.addStudentInCourse(username, courseId);
//        }
        courseServiceImplementation.addStudentInCourse(username, courseId);
        List<Student> studentList=courseServiceImplementation.listStudentsByCourse(courseId);
        model.addAttribute("lista_studenti", studentList);
        model.addAttribute("bodyContent","studentsInCourse");
        return "master-template";
    }

    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String studentEnrollmentSummaryAdmin(HttpServletRequest request, Model model) {
        Long courseId= (Long) request.getSession().getAttribute("courseId");
        model.addAttribute("ime_kurs", courseServiceImplementation.listAllCourses().stream().filter(r -> r.getCourseId().equals(courseId)).findFirst().get().getName());
        String username= (String) request.getSession().getAttribute("usernameSubmit");
        //String username1= (String) request.getSession().getAttribute("username");
        String username1=request.getRemoteUser();
        User user= (User) userServiceImplementation.loadUserByUsername(username1);
//        if(username1.equals("ROLE_ADMIN")){
//            courseServiceImplementation.addStudentInCourse(username, courseId);
//        }
        if(user.getRole().toString().equals("ROLE_ADMIN")){
            courseServiceImplementation.addStudentInCourse(username, courseId);
        }
        courseServiceImplementation.addStudentInCourse(username, courseId);
        List<Student> studentList=courseServiceImplementation.listStudentsByCourse(courseId);
        model.addAttribute("lista_studenti", studentList);
        model.addAttribute("bodyContent","studentsInCourse");
        return "master-template";
    }

    @PostMapping("/post")
    public String PostMethod(HttpServletRequest request) {

        return "redirect:/courses";
    }
}




