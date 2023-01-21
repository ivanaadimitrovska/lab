//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.model.Grade;
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.service.StudentService;
//import mk.ukim.finki.wp.lab.service.implementation.GradeServiceImplementation;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name="listStudent", urlPatterns = "/addStudent")
//public class ListStudentServlet extends HttpServlet {
//
//    private final StudentService studentService;
//    private final SpringTemplateEngine springTemplateEngine;
//    private final CourseService courseService;
//    private final GradeServiceImplementation gradeServiceImplementation;
//
//    public ListStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeServiceImplementation gradeServiceImplementation) {
//        this.studentService = studentService;
//        this.springTemplateEngine = springTemplateEngine;
//        this.courseService = courseService;
//        this.gradeServiceImplementation = gradeServiceImplementation;
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context=new WebContext(req, resp, req.getServletContext());
//        Long courseId=(Long) req.getSession().getAttribute("courseId");
//        context.setVariable("courseId", courseId);
//        List<Student> studenti=studentService.listAll();
//        //List<Student>se_vo=courseService.listStudentsByCourse(courseId);
//        //List<Student>ne_se_vo=studenti.stream().filter(r -> !se_vo.contains(r)).collect(Collectors.toList());
//        //context.setVariable("ne_se_vo", ne_se_vo);
//        context.setVariable("studenti", studenti);
//        //List<Grade> grades=this.gradeServiceImplementation.findAll();
//        //context.setVariable("oceni", grades);
//        this.springTemplateEngine.process("listStudents.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String newStudent = req.getParameter("createNew");
//        String oceni=req.getParameter("listGrades");
//        if(oceni!=null){
//            resp.sendRedirect("/courses/grades");
//        }
//        if(newStudent != null) {
//            req.getSession().setAttribute("createNew", newStudent);
//            resp.sendRedirect("/courses/addStudent");
//        }
//        String usernameSubmit = (String) req.getParameter("size");
//        if(usernameSubmit != null) {
//            req.getSession().setAttribute("usernameSubmit", usernameSubmit);
//            resp.sendRedirect("/studentEnrollmentSummary");
//        }
//    }
//}