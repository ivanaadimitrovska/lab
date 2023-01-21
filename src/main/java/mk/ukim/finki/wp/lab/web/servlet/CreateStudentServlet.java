//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.model.Grade;
//import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name="createStudent", urlPatterns = "/createStudent")
//public class CreateStudentServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final StudentServiceImplementation serviceImplementation;
//
//    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentServiceImplementation serviceImplementation) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.serviceImplementation = serviceImplementation;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context=new WebContext(req, resp, req.getServletContext());
//        springTemplateEngine.process("createStudent.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String ime=(String) req.getParameter("name");
//        String prezime=(String) req.getParameter("surname");
//        String username=(String) req.getParameter("username");
//        String password=(String) req.getParameter("password");
//
//        serviceImplementation.save(username, password, ime, prezime);
//        //req.getSession().setAttribute("ime", ime);
//        //req.getSession().setAttribute("prezime", prezime);
//        resp.sendRedirect("/addStudent");
//    }
//}
