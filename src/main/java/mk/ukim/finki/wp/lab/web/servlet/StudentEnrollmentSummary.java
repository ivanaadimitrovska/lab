//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.service.implementation.CourseServiceImplementation;
//import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name="studentEnrollment", urlPatterns = "/studentEnrollmentSummary")
//public class StudentEnrollmentSummary extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final CourseServiceImplementation courseServiceImplementation;
//    private final StudentServiceImplementation studentServiceImplementation;
//
//    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseServiceImplementation courseServiceImplementation, StudentServiceImplementation studentServiceImplementation) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.courseServiceImplementation = courseServiceImplementation;
//        this.studentServiceImplementation = studentServiceImplementation;
//    }
//
//    @Transactional
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext webContext=new WebContext(req, resp, req.getServletContext());
//        Long courseId= (Long) req.getSession().getAttribute("courseId");
//        webContext.setVariable("ime_kurs", courseServiceImplementation.listAllCourses().stream().filter(r -> r.getCourseId().equals(courseId)).findFirst().get().getName());
//        String username= (String) req.getSession().getAttribute("usernameSubmit");
//        courseServiceImplementation.addStudentInCourse(username, courseId);
//        List<Student> studentList=courseServiceImplementation.listStudentsByCourse(courseId);
//        webContext.setVariable("lista_studenti", studentList);
//        this.springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("/courses");
//    }
//}
