package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exception.ErrorId;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.implementation.CourseServiceImplementation;
import mk.ukim.finki.wp.lab.service.implementation.GradeServiceImplementation;
import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
import mk.ukim.finki.wp.lab.service.implementation.TeacherServiceImplementation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final TeacherServiceImplementation teacherServiceImplementation;
    private final CourseServiceImplementation courseServiceImplementation;
    private final StudentServiceImplementation studentServiceImplementation;
    private final GradeServiceImplementation gradeServiceImplementation;
    private final GradeRepository gradeRepository;

    public CourseController(TeacherServiceImplementation teacherServiceImplementation, CourseServiceImplementation courseServiceImplementation, StudentServiceImplementation studentServiceImplementation, GradeServiceImplementation gradeServiceImplementation,
                            GradeRepository gradeRepository) {
        this.teacherServiceImplementation = teacherServiceImplementation;
        this.courseServiceImplementation = courseServiceImplementation;
        this.studentServiceImplementation = studentServiceImplementation;
        this.gradeServiceImplementation = gradeServiceImplementation;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Course> courses = courseServiceImplementation.listAllCourses();
        courses=courses.stream().sorted(Comparator.comparing(Course::getName)).toList();
        model.addAttribute("lista", courses);
        model.addAttribute("bodyContent", "listCourses");
        return "master-template";
    }

    @PostMapping
    public String listCourses(HttpServletRequest request, @RequestParam(required = false) Long courseId) {
        request.getSession().setAttribute("courseId", courseId);
        //String courseId= request.getParameter("courseId");
        return "redirect:/liststudents";
    }

    @GetMapping("/listStudentsinCourse")
    public String listStudentsinCourse(Model model) {
        //model.addAttribute("bodyContent","studentsInCourse");
        return "studentsInCourse";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCourse(Model model) {
        List<Course> courses = courseServiceImplementation.listAllCourses();
        List<Student> students = studentServiceImplementation.listAll();
        List<Teacher> teachers = teacherServiceImplementation.findAll();
        model.addAttribute("profesori", teachers);
        return "add-course";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCourse(@PathVariable Long id, Model model) {
        Course kurs = courseServiceImplementation.CourseByCourseId(id).orElse(null);
        List<Teacher> teachers = teacherServiceImplementation.findAll();
        try {
            if (courseServiceImplementation.listAllCourses().stream().anyMatch(r -> r.getCourseId().equals(id))) {
                model.addAttribute("profesori", teachers);
                model.addAttribute("course", kurs);
                return "add-course";
            }else{
                throw new ErrorId(id);
            }
        }catch (ErrorId errorId){
            return "redirect:/courses?error=" + errorId.getMessage();
        }
    }

    @PostMapping("/add-save")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long id_prof, HttpServletRequest request,
                             @RequestParam(name="courseId", required = false) Long courseId)  {

        //Long courseId = (Long) request.getSession().getAttribute("courseId");
                             // Long courseId = (Long) request.getSession().getAttribute("courseId");
        if (courseId != null) {
            //this.courseServiceImplementation.delete(courseId);
            this.courseServiceImplementation.editCourse(name, description, id_prof, courseId);
        } else {
            this.courseServiceImplementation.save(name, description, id_prof);
        }
        return "redirect:/courses";
    }

    @GetMapping("/smeni-redosled")
    public String smeni(Model model, HttpServletRequest request){
        List<Course> courses = courseServiceImplementation.listAllCourses();
        courses=courses.stream().sorted(Comparator.comparing(Course::getName).reversed()).toList();
        model.addAttribute("lista", courses);
        request.getSession().setAttribute("obraten", courses);
        return "listCourses";
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCourse(@PathVariable Long id) {
        courseServiceImplementation.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/access_denied")
    public String access_denied(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model, HttpServletRequest request) {
        List<Grade> grades = gradeServiceImplementation.findAll();
        //Course course =courseServiceImplementation.CourseByCourseId(courseId).orElseThrow();
        model.addAttribute("oceni", grades);
        request.getSession().setAttribute("oceni", grades);
        //model.addAttribute("course", course);
        return "add-student";
    }

    @PostMapping("/add-save-student")
    public String saveStudent(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam(required = false) String grade,
                              @RequestParam("time") String timestamp,
                              //@RequestParam(required = false) Long courseId,
                              @RequestParam(required = false) Long gradeId, HttpServletRequest request) {

        LocalDateTime dateTime = LocalDateTime.parse(timestamp);
        //DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        //LocalDateTime localDateTime= LocalDateTime.parse(timestamp, dateTimeFormatter);
        Long courseId = (Long) request.getSession().getAttribute("courseId");
        Course course =courseServiceImplementation.CourseByCourseId(courseId).orElseThrow();
        Grade ocena=new Grade(grade,course, dateTime);
        //Grade ocena =new Grade(grade,course, timestamp);
        //Grade ocena=this.gradeServiceImplementation.save2(grade, course,timestamp).orElseThrow();
        //Grade grade1=gradeServiceImplementation.findById(gradeId).orElseThrow();
        this.studentServiceImplementation.save(username, password, name, surname, ocena);
        Student student= studentServiceImplementation.findByUsername(username).orElseThrow();
        //this.studentServiceImplementation.delete(student);
        //this.gradeServiceImplementation.delete(ocena);
        this.gradeServiceImplementation.delete(ocena);
        if(grade==null){
            this.gradeServiceImplementation.delete(ocena);
        }
        //Grade ocena1=new Grade(grade, student,course, timestamp);
        Grade ocena1=gradeServiceImplementation.save1(grade, student, course, dateTime).orElseThrow();
        //this.gradeServiceImplementation.save(ocena1);
        this.studentServiceImplementation.delete(student);
        this.studentServiceImplementation.save(username, password, name, surname, ocena1);
        return "redirect:/liststudents";
    }

    @GetMapping("/grades")
    public String grades(Model model, HttpServletRequest request) {
        List<Grade> grades = gradeServiceImplementation.findAll();
        List<Student> students=studentServiceImplementation.listAll();
        Long courseId = (Long) request.getSession().getAttribute("courseId");
        Course course =courseServiceImplementation.CourseByCourseId(courseId).orElseThrow();
        model.addAttribute("oceni", grades);
        model.addAttribute("students", students);
        model.addAttribute("imeNakurs", course.getName());
        //model.addAttribute("localDateTime", students);
        return "listGrades";
    }

    @PostMapping("/listGrades")
    public String listGrades() {
        return "redirect:/listStudents";
    }

//    @GetMapping("/findBetween")
//    public String findBetween(@RequestParam("from") String from, @RequestParam("to") String to, HttpServletRequest request, Model model){
//        Long courseId = (Long) request.getSession().getAttribute("courseId");
//        Course course =courseServiceImplementation.CourseByCourseId(courseId).orElseThrow();
//        List<Grade> grades_findBetween=this.gradeServiceImplementation.findBetween(LocalDateTime.parse(from), LocalDateTime.parse(to), course);
//        model.addAttribute("findBetween", grades_findBetween);
//        return "FindBetween";
//    }

    @GetMapping("/listGradesBetween")
    public String listGradesBetween(@RequestParam("from") String from,
                                    @RequestParam("to") String to, Model model,
                                    HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("courseId");
        Course course_grades =courseServiceImplementation.CourseByCourseId(id).orElseThrow();
        List<Grade> grades_findBetween=this.gradeServiceImplementation.findBetween(LocalDateTime.parse(from), LocalDateTime.parse(to), course_grades);
        request.getSession().setAttribute("findBetween", grades_findBetween);
        model.addAttribute("findBetween", grades_findBetween);
        return "FindBetween";
    }

    @GetMapping("/padnati")
    public String padnati(Model model){
        List<Student> students=studentServiceImplementation.listAll();
        List<Grade> grades=this.gradeServiceImplementation.padnati("6");
        model.addAttribute("padnati", grades);
        return "padnati";
    }
//    @PostMapping("/listajPadnati")
//    public String listajPadnati() {
//        return "redirect:/listStudents";
//    }

//    @GetMapping("/listGrades1")
//    public String listGrades1() {
//        return "redirect:/courses/listGradesBetween";
//    }

//    @GetMapping("/edit/{timestamp}")
//    public String editGrade(@PathVariable String timestamp, Model model) {
//        List<Grade> grades=gradeServiceImplementation.findAll();
//        model.addAttribute("grades_edit", grades);
//        return "add-student";

//        Course kurs = courseServiceImplementation.CourseByCourseId(id).orElse(null);
//        List<Teacher> teachers = teacherServiceImplementation.findAll();
//        try {
//            if (courseServiceImplementation.listAllCourses().stream().anyMatch(r -> r.getCourseId().equals(id))) {
//                model.addAttribute("profesori", teachers);
//                model.addAttribute("course", kurs);
//                return "add-course";
//            }else{
//                throw new ErrorId(id);
//            }
//        }catch (ErrorId errorId){
//            return "redirect:/courses?error=" + errorId.getMessage();
//        }

}
