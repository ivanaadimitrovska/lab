package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryjpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {
    private final CourseRepositoryjpa courseRepository;
    private final StudentRepositoryJpa studentRepository;
    private final TeacherRepositoryJpa teacherRepository;
    private final StudentServiceImplementation studentServiceImplementation;

    public CourseServiceImplementation(CourseRepositoryjpa courseRepository, StudentRepositoryJpa studentService, TeacherRepositoryJpa teacherRepository, StudentServiceImplementation studentServiceImplementation) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentService;
        this.teacherRepository = teacherRepository;
        this.studentServiceImplementation = studentServiceImplementation;
    }
    @Transactional
    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course course=courseRepository.findCourseByCourseId(courseId);
        return course.getStudents();
    }

    @Transactional
    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = this.studentRepository.findByUsername(username).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
//        if(course != null) {
//            if (course.getStudents().stream().filter(r -> r.getUsername().equals(username)).toList().size() > 0) {
//                throw new RuntimeException();
//            }
//        }

//        if(student!=null){
//            assert course != null;
//            course.getStudents().add(student);
//        }
            assert course != null;
//            Student student1 = course.getStudents().stream().filter(r -> r.getUsername().equals(username)).findFirst().orElseThrow();
//            studentServiceImplementation.delete(student1);
//            course.getStudents().add(student);
            if(student!=null) {
                course.getStudents().removeIf(r -> r.getUsername().equals(username));
                course.getStudents().add(student);
            }
                //studentRepository.deleteByUsername(username);
                //course.getStudents().add(student);

        //shoppingCart.getProductList().add(product);
        //return shoppingCartsRepository.save(shoppingCart);
        //assert course != null;
        return courseRepository.save(course);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    @Override
    public Course save(String name, String description, Long id) {
        this.courseRepository.deleteByName(name);
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        return courseRepository.save(new Course(name, description, teacher));
    }
    @Transactional
    @Override
    public void deleteById(Long courseId) {
        this.courseRepository.deleteById(courseId);
    }

    @Transactional
    @Override
    public void delete(Long courseId) {
        Course course=courseRepository.findById(courseId).orElseThrow();
        this.courseRepository.delete(course);
    }

    @Override
    public Optional<Course> CourseByCourseId(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseRepository.findAllByName(name);
    }

    @Transactional
    @Override
    public Optional<Course> editCourse(String name, String description, Long id, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        course.setName(name);
        course.setDescription(description);

        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        course.setTeacher(teacher);

        this.courseRepository.delete(course);
        return Optional.of(courseRepository.save(new Course(name,description,teacher)));
    }
}