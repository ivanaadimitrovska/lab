package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAllCourses();
    Optional<Course> CourseByCourseId(Long id);
    Course save(String name, String description, Long prof_id);
    void deleteById(Long id);
    void delete(Long id);
    Optional<Course> editCourse(String name, String description, Long prof_id, Long courseId);
    List<Course> findByName(String name);
}
