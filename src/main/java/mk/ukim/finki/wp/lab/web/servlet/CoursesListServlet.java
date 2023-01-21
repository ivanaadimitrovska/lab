//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.service.CourseService;
//import org.springframework.web.bind.annotation.PostMapping;
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
//@WebServlet(name="listCourses", urlPatterns = "/listCourses")
//public class CoursesListServlet extends HttpServlet {
//
//    private final CourseService courseService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//
//    public CoursesListServlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
//        this.courseService = courseService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context=new WebContext(req, resp, req.getServletContext());
//        List<Course> lista= courseService.listAllCourses();
//        context.setVariable("lista", lista);
//
//        springTemplateEngine.process("listCourses.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            String courseId= req.getParameter("courseId");
//            if(courseId!=null) {
//                Long Id = Long.valueOf(courseId);
//                req.getSession().setAttribute("courseId", Id);
//                resp.sendRedirect("/addStudent");
//            }
//            else{
//                resp.sendRedirect("/listCourses");
//            }
//    }
//}