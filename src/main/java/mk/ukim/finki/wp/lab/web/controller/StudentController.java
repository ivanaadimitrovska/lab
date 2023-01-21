//package mk.ukim.finki.wp.lab.web.controller;
//
//import mk.ukim.finki.wp.lab.model.Course;
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.model.Teacher;
//import mk.ukim.finki.wp.lab.service.implementation.StudentServiceImplementation;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Comparator;
//import java.util.List;
//
//@Controller
//@RequestMapping("/students")
//public class StudentController {
//
//    private final StudentServiceImplementation serviceImplementation;
//
//    public StudentController(StudentServiceImplementation serviceImplementation) {
//        this.serviceImplementation = serviceImplementation;
//    }
//
//    @GetMapping
//    public String getStudents(@RequestParam(required = false) String error, Model model) {
//        if (error != null && !error.isEmpty()) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
//        List<Student> studenti = serviceImplementation.listAll();
//        //studenti=studenti.stream().sorted(Comparator.comparing(Course::getName)).toList();
//        model.addAttribute("studenti", studenti);
//        return "listStudents";
//    }
//
//    @GetMapping("/addStudent")
//    public String addCourse() {
//        //List<Student> teachers = teacherServiceImplementation.findAll();
//        //model.addAttribute("profesori", teachers);
//        return "createStudent";
//    }
//
//    @PostMapping("/save")
//    public String saveStudent(@RequestParam String name,
//                              @RequestParam String surname,
//                              @RequestParam String username,
//                              @RequestParam String password) {
//        serviceImplementation.save(username, password, name, surname);
//        return "redirect:/students";
//    }
//
//}
